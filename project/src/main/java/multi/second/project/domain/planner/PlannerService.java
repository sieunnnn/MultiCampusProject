package multi.second.project.domain.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.gallery.dto.request.GalleryModifyRequest;
import multi.second.project.domain.gallery.dto.request.GalleryRegistRequest;
import multi.second.project.domain.gallery.dto.response.GalleryDetailResponse;
import multi.second.project.domain.gallery.dto.response.GalleryListResponse;
import multi.second.project.domain.gallery.repository.GalleryRepository;
import multi.second.project.domain.group.domain.TravelGroup;
import multi.second.project.domain.group.repository.TravelGroupRepository;
import multi.second.project.domain.host.domain.Host;
import multi.second.project.domain.host.repository.HostRepository;
import multi.second.project.domain.member.MemberRepository;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.member.dto.Principal;
import multi.second.project.domain.planner.domain.Planner;
import multi.second.project.domain.planner.dto.request.PlannerGroupModifyRequest;
import multi.second.project.domain.planner.dto.request.PlannerHostModifyRequest;
import multi.second.project.domain.planner.dto.request.PlannerRegistRequest;
import multi.second.project.domain.planner.dto.request.PlannerTitleModifyRequest;
import multi.second.project.domain.planner.dto.response.PlannerDetailResponse;
import multi.second.project.domain.planner.dto.response.PlannerListResponse;
import multi.second.project.domain.planner.repository.PlannerRepository;
import multi.second.project.infra.code.ErrorCode;
import multi.second.project.infra.exception.AuthException;
import multi.second.project.infra.exception.HandlableException;
import multi.second.project.infra.util.file.FilePath;
import multi.second.project.infra.util.file.FileRepository;
import multi.second.project.infra.util.file.FileUtil;
import multi.second.project.infra.util.file.dto.FilePathDto;
import multi.second.project.infra.util.file.dto.FileUploadDto;
import multi.second.project.infra.util.paging.Paging;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class PlannerService {
	
	private final PlannerRepository plannerRepository;
	private final TravelGroupRepository travelGroupRepository;
	private final HostRepository hostRepository;
	private final MemberRepository memberRepository;

	public Map<String, Object> findPlannerListByUserId(String userId, Pageable pageable) {
		
		Page<Planner> page = plannerRepository.findByTravelGroupMembersUserId(userId, pageable);
		
		Paging paging = Paging.builder()
						.page(page)
						.blockCnt(5)
						.build();
		
		
		return Map.of("plannerList",PlannerListResponse.toDtoList(page.getContent()), "paging", paging);
	}

	@Transactional
	public void createPlanner(PlannerRegistRequest dto) {
		
//		TravelGroup travelGroup = travelGroupRepository.findTravelGroupByMembersUserId(dto.getUserId());
//		Host host = hostRepository.findByMemberUserId(dto.getUserId());
		//이거 아냐...
		//또 빌더해서 만들어놔야돼
		Member member = memberRepository.findById(dto.getUserId()).get();
		
		//host 생성
		Host host = Host.createHost(member);//자신을 host로
		hostRepository.save(host);
		
		//travelGroup생성
		TravelGroup travelGroup = new TravelGroup();
		travelGroup.addMembers(member);//자신을 그룹멤버에 추가
		travelGroupRepository.save(travelGroup);
		
		//planner 생성
		Planner planner = Planner.createPlanner(dto, host, travelGroup);
		plannerRepository.saveAndFlush(planner);
	}

	@Transactional
	public void removePlanner(Long tpIdx, Principal principal) {
		
		Planner	planner = plannerRepository.findById(tpIdx)
					.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
//		if(!planner.getHost().getMember().getUserId().equals(principal.getUserId())) throw new AuthException(ErrorCode.HOST_UNAUTHORIZED_REQUEST);
//		plannerRepository.delete(planner);
		
		//삭제한사람이 host가 아닐경우 그플랜의 그룹 자신을 뺀다.
		if(!planner.getHost().getMember().getUserId().equals(principal.getUserId())) {
			Member member = memberRepository.findById(principal.getUserId()).get();
			
			TravelGroup travelGroup = travelGroupRepository.findById(planner.getTravelGroup().getTgIdx())
					.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
			
			travelGroup.removeMembers(member);
			
			travelGroupRepository.save(travelGroup);
		}
		else {//삭제한 사람이 host일 경우 방을 삭제한다.
			plannerRepository.delete(planner);
		}
		
	}

	public PlannerDetailResponse findPlannerBytpIdx(Long tpIdx) {
		
		Planner planner = plannerRepository.findById(tpIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		return new PlannerDetailResponse(planner);
	}
	
	@Transactional
	public void updatePlannerTitle(PlannerTitleModifyRequest dto, Principal principal) {
		// TODO Auto-generated method stub
		Planner planner = plannerRepository.findById(dto.getTpIdx())
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		if(!planner.getHost().getMember().getUserId().equals(principal.getUserId())) throw new AuthException(ErrorCode.HOST_UNAUTHORIZED_REQUEST);
		
		planner.updatePlannerTitle(dto);
		
		plannerRepository.flush();
		
	}

	@Transactional
	public void updatePlannerHost(PlannerHostModifyRequest dto, Principal principal) {
		Planner planner = plannerRepository.findById(dto.getTpIdx())
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		if(!planner.getHost().getMember().getUserId().equals(principal.getUserId())) throw new AuthException(ErrorCode.HOST_UNAUTHORIZED_REQUEST);
		
		planner.updatePlannerHost(dto);
		
		plannerRepository.flush();
	}

	@Transactional
	public void addPlannerGroup(PlannerGroupModifyRequest dto, Principal principal) {
		// TODO Auto-generated method stub
		Member member = memberRepository.findById(dto.getNewUserId()).get();
		
		Planner planner = plannerRepository.findById(dto.getTpIdx())
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		TravelGroup travelGroup = travelGroupRepository.findById(planner.getTravelGroup().getTgIdx())
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		travelGroup.addMembers(member);
		
		travelGroupRepository.save(travelGroup);
		
		
	}

//	public void updatePlannerGroup(PlannerGroupModifyRequest dto, Principal principal) {
//		Planner planner = plannerRepository.findById(dto.getTpIdx())
//				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
//		if(!planner.getHost().getMember().getUserId().equals(principal.getUserId())) throw new AuthException(ErrorCode.HOST_UNAUTHORIZED_REQUEST);
//		
//		
//		planner.getTravelGroup().getMembers().u
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private final GalleryRepository galleryRepository;
//	private final MemberRepository memberRepository;
//	private final FileRepository fileRepository;
//	private final FileUtil fileUtil;
//	
//	@Transactional
//	public void createGallery(GalleryRegistRequest dto, List<MultipartFile> files) {
//		
//		Member member = memberRepository.findById(dto.getUserId()).get();
//		Gallery gallery = Gallery.createGallery(dto, member);
//		
//		
//		FilePathDto filePath = new FilePathDto();
//		filePath.setGroupName("gallery");
//		
//		List<FileUploadDto> fileUploadDtos = fileUtil.generateFileUploadDtos("gallery", files);
//		
//		fileUploadDtos.forEach(e -> {
//			gallery.addFile(FilePath.createFilePath(e.getFilePathDto()));
//		});
//		
//		// JPA가 변경된 내용을 데이터베이스에 반영
//		galleryRepository.saveAndFlush(gallery);
//		
//		fileUtil.uploadFile(fileUploadDtos);
//	}
//	//이건 이제 안쓸듯
//	public Map<String, Object> findGalleryList(Pageable pageable) {
//		//findAll 을 findByUserId로 바꿀 예정
//		System.out.println("pageable :  "+pageable);
//		Page<Gallery> page = galleryRepository.findAll(pageable);
//		System.out.println("galleryRepository.findAll(pageable) :  "+page);
//		
////		Page<Gallery> page2 = galleryRepository.findByMemberUserId("group2B",pageable);
////		System.out.println("galleryRepository.findByMemberUserId(\"group2B\",pageable) :  "+page2);
//		
//		Paging paging = Paging.builder()
//				.page(page)
//				.blockCnt(5)
//				.build();
//		
////		System.out.println("page.getContent() :  " +page.getContent());
////		System.out.println("GalleryListResponse.toDtoList(page.getContent()) :  "+ GalleryListResponse.toDtoList(page.getContent()));
////		System.out.println("----------------------------");
////		System.out.println("page2.getContent() :  " +page2.getContent());
////		System.out.println("GalleryListResponse.toDtoList(page2.getContent()) :  "+ GalleryListResponse.toDtoList(page2.getContent()));
//		return Map.of("galleryList",GalleryListResponse.toDtoList(page.getContent()), "paging", paging);
//	}
//	
//	//특정 유저의 게시물 리스트만 가져오는 코드
//	public Map<String, Object> findGalleryListByUserId(String userId, Pageable pageable) {
//		
//		Page<Gallery> page = galleryRepository.findByMemberUserId(userId,pageable);
//		System.out.println("galleryRepository.findByMemberUserId(userId,pageable) :  "+page);
//		
//		Paging paging = Paging.builder()
//				.page(page)
//				.blockCnt(5)
//				.build();
//		
//		System.out.println("page.getContent() :  " +page.getContent());
//		System.out.println("GalleryListResponse.toDtoList(page.getContent()) :  "+ GalleryListResponse.toDtoList(page.getContent()));
//		return Map.of("galleryList",GalleryListResponse.toDtoList(page.getContent()), "paging", paging);
//	}
//	
//	//포스트의 댓글 가져오는 코드
////	public Map<String, Object> findCommentListByPostId(Long postIdx) {
////		
////		
////		return Map.of("commentList",CommentListResponse.toDtoList(galleryRepository.findByGalleryPostIdx(postIdx)));
////	}
//	
//
//	public GalleryDetailResponse findGalleryByPostIdx(Long postIdx) {
//		Gallery gallery = galleryRepository.findById(postIdx)
//						.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
//		
//		return new GalleryDetailResponse(gallery);
//	}
//
//	public FilePathDto findFilePathByFpIdx(Long fpIdx) {
//		FilePath filePath = fileRepository.findById(fpIdx)
//						.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
//		
//		return new FilePathDto(filePath);
//	}
//
//	@Transactional
//	public void updateGallery(GalleryModifyRequest dto, List<MultipartFile> files) {
//
//		Gallery gallery = galleryRepository.findById(dto.getPostIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
//		if(!gallery.getMember().getUserId().equals(dto.getUserId())) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
//		
//		gallery.updateGallery(dto);
//		
//		List<FilePathDto> delFilePath = new ArrayList<FilePathDto>();
//		
//		//사용자가 삭제한 파일을 지워주기
//		dto.getDelFiles().forEach(e -> {
//			FilePath filePath = fileRepository.findById(e).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
//			delFilePath.add(new FilePathDto(filePath));
//			gallery.removeFile(filePath);
//		});
//		
//		
//		List<FileUploadDto> fileUploadDtos = fileUtil.generateFileUploadDtos("gallery", files);
//		
//		fileUploadDtos.forEach(e -> {
//			gallery.addFile(FilePath.createFilePath(e.getFilePathDto()));
//		});
//		
//		
//		// 엔티티 변경사항을 데이터베이스에 반영
//		galleryRepository.flush();
//		
//		// 파일을 삭제 및 추가
//		fileUtil.uploadFile(fileUploadDtos);
//		
//		delFilePath.forEach(e -> {
//			fileUtil.deleteFile(e);
//		});
//	}
//
//	@Transactional
//	public void removeGallery(Long postIdx, Principal principal) {
//		Gallery gallery = galleryRepository.findById(postIdx)
//					.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
//		
//		if(!gallery.getMember().getUserId().equals(principal.getUserId())) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
//		
//		List<FilePathDto> filePathDtos = gallery.getFiles()
//									.stream().map(e -> new FilePathDto(e)).collect(Collectors.toList());
//		
//		galleryRepository.delete(gallery);
//		
//		filePathDtos.forEach(e ->{
//			fileUtil.deleteFile(e);
//		});
//		
//	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
