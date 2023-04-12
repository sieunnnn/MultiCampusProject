package multi.second.project.domain.gallery;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import multi.second.project.domain.profile.domain.Profile;
import multi.second.project.domain.profile.service.ProfileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import multi.second.project.domain.comment.CommentService;
import multi.second.project.domain.gallery.dto.request.GalleryModifyRequest;
import multi.second.project.domain.gallery.dto.request.GalleryRegistRequest;
import multi.second.project.domain.gallery.dto.response.GalleryDetailResponse;
import multi.second.project.domain.member.UserPrincipal;
import multi.second.project.infra.util.file.dto.FilePathDto;

@Controller
@AllArgsConstructor
@RequestMapping("gallery")
public class GalleryController {

	private final GalleryService galleryService;
	private final CommentService commentService;

	private final ProfileService profileService;

	//갤러리 포스트 추가 폼
	@GetMapping("add")
	public String galleryForm(Model model) {
		model.addAttribute("user", UserPrincipal.getUserPrincipal().getPrincipal().getUserId());
		return "gallery/add";
	}

	//파일(사진) 업로드시
	@PostMapping("upload")
	public String upload(//업로드 했을때 사진이 보이게 해야될것 같은데 어떻게 해야 할까?
						 @RequestParam List<MultipartFile> files,
						 //@SessionAttribute(name="auth", required=false) Principal principal,
						 GalleryRegistRequest dto)
	{ //dto.setUserId(principal.getUserId());
		dto.setUserId(UserPrincipal.getUserPrincipal().getUserId()); //로그인한 사람의 아이디를 받아 저장
		galleryService.createGallery(dto, files); // 받은 정보와 파일들로 서비스에서 등록처리

		return "redirect:/gallery/list";
	}

	//갤러리 리스트 화면
	@GetMapping("list")
	public String galleryList(//등록글들을 타이틀이 아닌 그림으로 보여줘야 하는데 어떻게 할까?
							  @PageableDefault(size=12, sort="postIdx", direction = Direction.DESC, page = 0)
//			@SessionAttribute(name="auth", required=false) Principal principal,//현재 아이디정보를 얻기위해 추가
							  Pageable pageable,
							  Model model

	) {
		//갤러리 리스트를 찾을 때 UserId기준으로 찾아야하는데 어떻게 할까? 완료
		//Map<String, Object> commandMap = galleryService.findGalleryList(pageable);
		//System.out.println("galleryService.findGalleryList(pageable) : "+commandMap);

//		Map<String, Object> commandMap = galleryService.findGalleryListByUserId(principal.getUserId(),pageable);
		Map<String, Object> commandMap = galleryService.findGalleryListByUserId(UserPrincipal.getUserPrincipal().getPrincipal().getUserId(),pageable);
		System.out.println("galleryService.findGalleryListByUserId(principal.getUserId(),pageable) : "+commandMap);
		model.addAllAttributes(commandMap);
		
		model.addAttribute("user", UserPrincipal.getUserPrincipal().getPrincipal().getUserId());

		Profile profile = profileService.getProfileData(UserPrincipal.getUserPrincipal().getPrincipal().getUserId());
		model.addAttribute("profile", profile);

		return "gallery/list";
	}
	
	
	//갤러리 리스트 화면
		@GetMapping("other-gallery")
		public String otherGalleryList(
								  @PageableDefault(size=12, sort="postIdx", direction = Direction.DESC, page = 0)
								  Pageable pageable,
								  Model model,
								  String profileId

		) {
			Map<String, Object> commandMap = galleryService.findGalleryListByUserId(profileId,pageable);
			System.out.println("galleryService.findGalleryListByUserId(principal.getUserId(),pageable) : "+commandMap);
			model.addAllAttributes(commandMap);
			
			model.addAttribute("user", profileId);

			Profile profile = profileService.getProfileData(UserPrincipal.getUserPrincipal().getPrincipal().getUserId());
			model.addAttribute("profile", profile);

			return "gallery/list";
		}
	

	//갤러리 특정 포스트 방문시
	@GetMapping("detail")
	public String galleryDetail(Long postIdx, Model model) {
		//포스트 번호로 특정 포스트를 찾는다
		GalleryDetailResponse dto = galleryService.findGalleryByPostIdx(postIdx);
		model.addAttribute("gallery", dto);

		Profile profile = profileService.getProfileData(UserPrincipal.getUserPrincipal().getPrincipal().getUserId());
		model.addAttribute("profile", profile);
		
		model.addAttribute("user", dto.getUserId());

//		//특정 포스트의 댓글을 가져오는 코드 (확인필요)(필요없을듯)
//		System.out.println("galleryService.findCommentListByPostIdx(postIdx) :  "+galleryService.findCommentListByPostIdx(postIdx));
//		model.addAttribute("comment", galleryService.findCommentListByPostIdx(postIdx));

		return "gallery/detail";
	}
	//
//	//갤러리 특정 포스트에서 댓글 작성 시
//	@PostMapping("comment")
//	public String comment(
//			@SessionAttribute(name="auth", required=false) Principal principal,
//			CommentRegistRequest dto
//			) {
//
//		return "redirect:/";
//	}
//
//	//갤러리 특정 포스트에서 댓글 삭제 시(is_del이 true일 경우 "삭제된 댓글입니다."로 보여지게 할 것)
//	@PostMapping("comment-delete")
//	public String commentDelete(
//			@SessionAttribute(name="auth", required=false) Principal principal
//			) {
//
//		return "redirect:/";
//	}
//
//	//갤러리 특정 포스트에서 댓글 수정 시(해야되나?)
//	@PostMapping("comment-modify")
//	public String commentModify(
//			@SessionAttribute(name="auth", required=false) Principal principal,
//			CommentModifyRequest dto
//			) {
//
//		return "redirect:/";
//	}
//
	//갤러리 포스트에서 사진 다운로드 시(필요 없을지도)
	@GetMapping("download")
	public ResponseEntity<FileSystemResource> downloadFile(Long fpIdx){

		FilePathDto dto = galleryService.findFilePathByFpIdx(fpIdx);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDisposition(ContentDisposition.builder("attachment")
				.filename(dto.getOriginFileName(), Charset.forName("utf-8"))
				.build());

		FileSystemResource fsr = new FileSystemResource(dto.getFullPath());
		return ResponseEntity.ok().headers(headers).body(fsr);
	}

	//갤러리 포스트 수정 화면
	@GetMapping("modify")
	public String galleryModify(Long postIdx, Model model) {
		GalleryDetailResponse dto = galleryService.findGalleryByPostIdx(postIdx);
		model.addAttribute("user", dto.getUserId());
		model.addAttribute("gallery", dto);
		return "gallery/modify";

	}
	//갤러리 포스트 수정완료시
	@PostMapping("modify")
	public String modify(GalleryModifyRequest dto,
						 @RequestParam List<MultipartFile> fileList
//						,@SessionAttribute("auth") Principal principal
	) {

//		dto.setUserId(principal.getUserId());
		dto.setUserId(UserPrincipal.getUserPrincipal().getUserId());
		galleryService.updateGallery(dto, fileList);

		return "redirect:/gallery/detail?postIdx="+dto.getPostIdx();
	}
	//갤러리 포스트 삭제시
	@PostMapping("remove")
	public String remove(Long postIdx
//			,@SessionAttribute("auth") Principal principal
	) {

//		galleryService.removeGallery(postIdx, principal);
		galleryService.removeGallery(postIdx, UserPrincipal.getUserPrincipal().getPrincipal());

		return "redirect:/gallery/list";
	}



























}