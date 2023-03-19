package multi.second.project.domain.gallery;

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
import multi.second.project.domain.comment.domain.Comment;
import multi.second.project.domain.comment.dto.request.CommentRegistRequest;
import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.gallery.dto.request.GalleryModifyRequest;
import multi.second.project.domain.gallery.dto.request.GalleryRegistRequest;
import multi.second.project.domain.gallery.dto.response.GalleryDetailResponse;
import multi.second.project.domain.gallery.dto.response.GalleryListResponse;
import multi.second.project.domain.gallery.repository.GalleryRepository;
import multi.second.project.domain.member.MemberRepository;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.member.dto.Principal;
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
public class GalleryService {

	private final GalleryRepository galleryRepository;
	private final MemberRepository memberRepository;
	private final FileRepository fileRepository;
	private final FileUtil fileUtil;
	
	@Transactional
	public void createGallery(GalleryRegistRequest dto, List<MultipartFile> files) {
		
		Member member = memberRepository.findById(dto.getUserId()).get();
		Gallery gallery = Gallery.createGallery(dto, member);
		
		
		FilePathDto filePath = new FilePathDto();
		filePath.setGroupName("gallery");
		
		List<FileUploadDto> fileUploadDtos = fileUtil.generateFileUploadDtos("gallery", files);
		
		fileUploadDtos.forEach(e -> {
			gallery.addFile(FilePath.createFilePath(e.getFilePathDto()));
		});
		
		// JPA가 변경된 내용을 데이터베이스에 반영
		galleryRepository.saveAndFlush(gallery);
		
		fileUtil.uploadFile(fileUploadDtos);
	}

	//특정 유저의 게시물 리스트만 가져오는 코드
	public Map<String, Object> findGalleryListByUserId(String userId, Pageable pageable) {

		Page<Gallery> page = galleryRepository.findByMemberUserId(userId,pageable);

		System.out.println("galleryRepository.findByMemberUserId(userId,pageable) :  "+page);

		Paging paging = Paging.builder()
				.page(page)
				.blockCnt(5)
				.build();

		System.out.println("page.getContent() :  " +page.getContent());
		System.out.println("GalleryListResponse.toDtoList(page.getContent()) :  "+ GalleryListResponse.toDtoList(page.getContent()));
		return Map.of("galleryList",GalleryListResponse.toDtoList(page.getContent()), "paging", paging);
	}
	
	//포스트의 댓글 가져오는 코드
//	public Map<String, Object> findCommentListByPostId(Long postIdx) {
//		
//		
//		return Map.of("commentList",CommentListResponse.toDtoList(galleryRepository.findByGalleryPostIdx(postIdx)));
//	}
	//포스트의 댓글 가져오는 코드
//	public List<CommentListResponse> findCommentListByPostIdx(Long postIdx) {
//		
//		return CommentListResponse.toDtoList(commentRepository.findByGalleryPostIdx(postIdx));
//	}
//	
//	public List<Gallery> findCommentListByPostIdx(Long postIdx){
//		
//		return galleryRepository.findCommentByPostIdx(postIdx);
//	}

	public GalleryDetailResponse findGalleryByPostIdx(Long postIdx) {
		Gallery gallery = galleryRepository.findById(postIdx)
						.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		return new GalleryDetailResponse(gallery);
	}

	public FilePathDto findFilePathByFpIdx(Long fpIdx) {
		FilePath filePath = fileRepository.findById(fpIdx)
						.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		return new FilePathDto(filePath);
	}

	@Transactional
	public void updateGallery(GalleryModifyRequest dto, List<MultipartFile> files) {

		Gallery gallery = galleryRepository.findById(dto.getPostIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		if(!gallery.getMember().getUserId().equals(dto.getUserId())) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
		
		gallery.updateGallery(dto);
		
		List<FilePathDto> delFilePath = new ArrayList<FilePathDto>();
		
		//사용자가 삭제한 파일을 지워주기
		dto.getDelFiles().forEach(e -> {
			FilePath filePath = fileRepository.findById(e).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
			delFilePath.add(new FilePathDto(filePath));
			gallery.removeFile(filePath);
		});
		
		
		List<FileUploadDto> fileUploadDtos = fileUtil.generateFileUploadDtos("gallery", files);
		
		fileUploadDtos.forEach(e -> {
			gallery.addFile(FilePath.createFilePath(e.getFilePathDto()));
		});
		
		
		// 엔티티 변경사항을 데이터베이스에 반영
		galleryRepository.flush();
		
		// 파일을 삭제 및 추가
		fileUtil.uploadFile(fileUploadDtos);
		
		delFilePath.forEach(e -> {
			fileUtil.deleteFile(e);
		});
	}
	
//	@Transactional
//	public void createComment(CommentRegistRequest dto) {
//		// TODO Auto-generated method stub
//		Member member = memberRepository.findById(dto.getUserId()).get();
//		Comment comment = Comment.createComment(dto, member);
//		
////		Gallery gallery = galleryRepository.findById(null).get();
////				addComment(comment);
//		
//		// JPA가 변경된 내용을 데이터베이스에 반영
//		commentRepository.saveAndFlush(comment);
//		
//		
//	}
	
	

	@Transactional
	public void removeGallery(Long postIdx, Principal principal) {
		Gallery gallery = galleryRepository.findById(postIdx)
					.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		if(!gallery.getMember().getUserId().equals(principal.getUserId())) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
		
		List<FilePathDto> filePathDtos = gallery.getFiles()
									.stream().map(e -> new FilePathDto(e)).collect(Collectors.toList());
		
		galleryRepository.delete(gallery);
		
		filePathDtos.forEach(e ->{
			fileUtil.deleteFile(e);
		});
		
		
	}


}
