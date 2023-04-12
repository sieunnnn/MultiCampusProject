package multi.second.project.domain.planner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.planner.domain.Participant;
import multi.second.project.domain.profile.domain.Profile;
import multi.second.project.domain.profile.dto.request.ProfileModifyRequest;
import multi.second.project.domain.profile.service.ProfileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import multi.second.project.domain.comment.CommentService;
import multi.second.project.domain.comment.dto.request.CommentModifyRequest;
import multi.second.project.domain.comment.dto.request.CommentRegistRequest;
import multi.second.project.domain.gallery.dto.request.GalleryModifyRequest;
import multi.second.project.domain.gallery.dto.request.GalleryRegistRequest;
import multi.second.project.domain.gallery.dto.response.GalleryDetailResponse;
import multi.second.project.domain.member.UserPrincipal;
import multi.second.project.domain.member.dto.Principal;
import multi.second.project.domain.planner.domain.Planner;
import multi.second.project.domain.planner.dto.request.PlannerGroupModifyRequest;
import multi.second.project.domain.planner.dto.request.PlannerGroupSearchRequest;
import multi.second.project.domain.planner.dto.request.PlannerHostModifyRequest;
import multi.second.project.domain.planner.dto.request.PlannerPrivateModifyRequest;
import multi.second.project.domain.planner.dto.request.PlannerRegistRequest;
import multi.second.project.domain.planner.dto.request.PlannerTitleModifyRequest;
import multi.second.project.domain.planner.dto.response.PlannerDetailResponse;
import multi.second.project.infra.util.file.dto.FilePathDto;

@Controller
@AllArgsConstructor
@RequestMapping("planner")
public class PlannerController {
	
	private final PlannerService plannerService;
	private final SimpMessagingTemplate simpMessagingTemplate;

	private final ProfileService profileService;
	
	//planner 리스트 뷰
	@GetMapping("list")
	public String plannerList(
			@PageableDefault(size=10, sort="tpIdx", direction = Direction.DESC, page = 0)
			Pageable pageable,
			Model model
			) {
		
		Map<String, Object> commandMap = plannerService.findPlannerListByUserId(UserPrincipal.getUserPrincipal().getPrincipal().getUserId(),pageable);
		model.addAllAttributes(commandMap);

		model.addAttribute("user", UserPrincipal.getUserPrincipal().getPrincipal().getUserId());
		
		Profile profile = profileService.getProfileData(UserPrincipal.getUserPrincipal().getPrincipal().getUserId());
		model.addAttribute("profile", profile);
		
		return "/planner/list";
	}
	
	//다른사람의 플래너를 볼 때
	@GetMapping("other-list")
	public String plannerOtherList(
			@PageableDefault(size=10, sort="tpIdx", direction = Direction.DESC, page = 0)
			Pageable pageable,
			Model model,
			String profileId//다른사람 프로필에서 플래너 보기를 누를때
			//<a th:href="@{|/planner/other-list?profileId=${profile.member.userId}|}"></a>
			) {
		
		//다른사람의 플래너를 볼 수 있지만 편집은 불가해야한다. 
		//-> 어떻게 할까? 일단 백앤드에서 예외처리를하자 그룹인원이 아니면 권한없다고 하도록
		Map<String, Object> commandMap = plannerService.findPlannerListByUserId(profileId,pageable);
		model.addAllAttributes(commandMap);
		
		model.addAttribute("user", profileId);

		Profile profile = profileService.getProfileData(UserPrincipal.getUserPrincipal().getPrincipal().getUserId());
		model.addAttribute("profile", profile);
		
		return "/planner/list";
	}
	
	//planner추가
	@PostMapping("create")
	public String create(
			PlannerRegistRequest dto) {
		
		dto.setUserId(UserPrincipal.getUserPrincipal().getUserId());
		plannerService.createPlanner(dto);

		
		return "redirect:/planner/list";
	}
	
	//planner 삭제(host여야지만 삭제할 수 있다.)
	@PostMapping("remove")
	public String remove(Long tpIdx) {
		
		plannerService.removePlanner(tpIdx, UserPrincipal.getUserPrincipal().getPrincipal());
		
		return "redirect:/planner/list";
	}
	
	//planner detail(플래너 하위 요소-todolist, todo 데이터 가져오기)
	@GetMapping("detail")
	public String plannerDetail(Long tpIdx, Model model) {
		
		PlannerDetailResponse dto = plannerService.findPlannerBytpIdx(tpIdx);

		model.addAttribute("planner", dto);

		Member member = new Member();

		Profile profile = profileService.getProfileData(UserPrincipal.getUserPrincipal().getPrincipal().getUserId());
		model.addAttribute("profile", profile);

		return "/planner/planner1";
	}

	
	
	//planner 이름 변경(host가 변경가능)
	@PostMapping("modify-title")
	public String modifyTitle(PlannerTitleModifyRequest dto) {
		plannerService.updatePlannerTitle(dto, UserPrincipal.getUserPrincipal().getPrincipal());
		
		return "redirect:/planner/list";
	}
	
	@PostMapping("modify-private")
	public String modifyTitle(PlannerPrivateModifyRequest dto) {
		plannerService.updatePlannerPrivate(dto, UserPrincipal.getUserPrincipal().getPrincipal());
		
		return "redirect:/planner/list";
	}
	
//	//planner host 변경(기본 만든사람)//host변경기능은 중요기능이 아니기 때문에 빼도 될듯
//	@PostMapping("modify-host")
//	public String modifyHost(PlannerHostModifyRequest dto) {
//		plannerService.updatePlannerHost(dto, UserPrincipal.getUserPrincipal().getPrincipal());
//		
//		return "redirect:/planner/detail?tpIdx="+dto.getTpIdx();
//	}
	
	//planner 공유인원 변경
//	@PostMapping("modify-group")
//	public String modifyGroup(PlannerGroupModifyRequest dto) {
//		plannerService.updatePlannerGroup(dto, UserPrincipal.getUserPrincipal().getPrincipal());
//		
//		return "redirect:/planner/detail?tpIdx="+dto.getTpIdx();
//	}
	
	//planner 공유인원 한명추가 Post 
//	@PostMapping("add-group")
//	public String addGroup(PlannerGroupModifyRequest dto) {
//	plannerService.addPlannerGroup(dto);
//	
//	return "redirect:/planner/detail?tpIdx="+dto.getTpIdx();
//}
	//planner 공유인원 한명추가 Message 
		@MessageMapping("/add-group/{tpIdx}")
		public void addGroup(
				@DestinationVariable("tpIdx") Long tpIdx,
				PlannerGroupModifyRequest dto) throws Exception {
			simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
					Map.of("type","add-group","msg",plannerService.addPlannerGroup(dto, tpIdx)));
		
	}
		//planner 공유할 인원 검색 
		@MessageMapping("/search-group/{tpIdx}")
		public void searchGroup(
				@DestinationVariable("tpIdx") Long tpIdx,
				PlannerGroupSearchRequest dto) throws Exception {
			simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
					Map.of("type","search-group","msg",plannerService.searchPlannerGroup(dto)));
		
	}
	
	//planner 공유인원 추방? 필요한가?

	// 날씨
	@GetMapping("/weather")
	public String weather()  {

		return "planner/weather";
	}



    
//	private final PlannerService galleryService;
//	private final CommentService commentService;
//	
//	//갤러리 포스트 추가 폼
//	@GetMapping("form")
//	public String galleryForm() {
//		return "/gallery/gallery-form";
//	}
//	
//	//파일(사진) 업로드시
//	@PostMapping("upload")
//	public String upload(//업로드 했을때 사진이 보이게 해야될것 같은데 어떻게 해야 할까?
//			@RequestParam List<MultipartFile> files,
//			@SessionAttribute(name="auth", required=false) Principal principal,
//			GalleryRegistRequest dto
//			) {
//		
//		dto.setUserId(principal.getUserId());
//		galleryService.createGallery(dto, files);
//		
//		return "redirect:/";
//	}
//	
//	//갤러리 리스트 화면
//	@GetMapping("list")
//	public String galleryList(//등록글들을 타이틀이 아닌 그림으로 보여줘야 하는데 어떻게 할까?
//			@PageableDefault(size=10, sort="postIdx", direction = Direction.DESC, page = 0)
//			@SessionAttribute(name="auth", required=false) Principal principal,//현재 아이디정보를 얻기위해 추가
//			Pageable pageable,
//			Model model
//			
//			) {
//		//갤러리 리스트를 찾을 때 UserId기준으로 찾아야하는데 어떻게 할까? 완료
//		//Map<String, Object> commandMap = galleryService.findGalleryList(pageable);
//		//System.out.println("galleryService.findGalleryList(pageable) : "+commandMap);
//		
//		Map<String, Object> commandMap = galleryService.findGalleryListByUserId(principal.getUserId(),pageable);
//		System.out.println("galleryService.findGalleryListByUserId(principal.getUserId(),pageable) : "+commandMap);
//		model.addAllAttributes(commandMap);
//		
//		return "/gallery/gallery-list";
//	}
//	
//	//갤러리 특정 포스트 방문시
//	@GetMapping("detail")
//	public String galleryDetail(Long postIdx, Model model) {
//		//포스트 번호로 특정 포스트를 찾는다
//		GalleryDetailResponse dto = galleryService.findGalleryByPostIdx(postIdx);
//		model.addAttribute("gallery", dto);
//		
//		//특정 포스트의 댓글을 가져오는 코드 (확인필요)
////		List<CommentListResponse> dto2 = commentService.findCommentListByPostIdx(postIdx);
////		model.addAttribute("comment", dto2);
//		
//		return "/gallery/gallery-contents";
//	}
////	
////	//갤러리 특정 포스트에서 댓글 작성 시
////	@PostMapping("comment")
////	public String comment(
////			@SessionAttribute(name="auth", required=false) Principal principal,
////			CommentRegistRequest dto
////			) {
////		
////		return "redirect:/";
////	}
////	
////	//갤러리 특정 포스트에서 댓글 삭제 시(is_del이 true일 경우 "삭제된 댓글입니다."로 보여지게 할 것) 
////	@PostMapping("comment-delete")
////	public String commentDelete(
////			@SessionAttribute(name="auth", required=false) Principal principal
////			) {
////		
////		return "redirect:/";
////	}
////	
////	//갤러리 특정 포스트에서 댓글 수정 시(해야되나?)
////	@PostMapping("comment-modify")
////	public String commentModify(
////			@SessionAttribute(name="auth", required=false) Principal principal,
////			CommentModifyRequest dto
////			) {
////		
////		return "redirect:/";
////	}
////	
//	//갤러리 포스트에서 사진 다운로드 시(필요 없을지도)
//	@GetMapping("download")
//	public ResponseEntity<FileSystemResource> downloadFile(Long fpIdx){
//		
//		FilePathDto dto = galleryService.findFilePathByFpIdx(fpIdx);
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//		headers.setContentDisposition(ContentDisposition.builder("attachment")
//									.filename(dto.getOriginFileName(), Charset.forName("utf-8"))
//									.build());
//		
//		FileSystemResource fsr = new FileSystemResource(dto.getFullPath());
//		return ResponseEntity.ok().headers(headers).body(fsr);
//	}
//	
//	//갤러리 포스트 수정 화면
//	@GetMapping("modify")
//	public String galleryModify(Long postIdx, Model model) {
//		GalleryDetailResponse dto = galleryService.findGalleryByPostIdx(postIdx);
//		model.addAttribute("gallery", dto);
//		return "gallery/gallery-modify";
//	}
//	//갤러리 포스트 수정완료시
//	@PostMapping("modify")
//	public String modify(GalleryModifyRequest dto,
//						@RequestParam List<MultipartFile> fileList,
//						@SessionAttribute("auth") Principal principal
//			) {
//		
//		dto.setUserId(principal.getUserId());
//		galleryService.updateGallery(dto, fileList);
//		
//		return "redirect:/gallery/detail?postIdx="+dto.getPostIdx();
//	}
//	//갤러리 포스트 삭제시
//	@PostMapping("remove")
//	public String remove(Long postIdx, @SessionAttribute("auth") Principal principal) {
//		
//		galleryService.removeGallery(postIdx, principal);
//		
//		return "redirect:/gallery/list";
//	}
//	
	
	
	
	
}
