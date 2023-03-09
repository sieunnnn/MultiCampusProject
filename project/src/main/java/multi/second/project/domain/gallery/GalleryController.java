package multi.second.project.domain.gallery;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import multi.second.project.domain.gallery.dto.request.GalleryModifyRequest;
import multi.second.project.domain.gallery.dto.request.GalleryRegistRequest;
import multi.second.project.domain.gallery.dto.response.GalleryDetailResponse;
import multi.second.project.domain.member.dto.Principal;
import multi.second.project.infra.util.file.dto.FilePathDto;

@Controller
@AllArgsConstructor
@RequestMapping("gallery")
public class GalleryController {

	private final GalleryService galleryService;
	
	//갤러리 포스트 추가 폼
	@GetMapping("form")
	public String galleryForm() {
		return "/gallery/gallery-form";
	}
	
	//파일(사진) 업로드시
	@PostMapping("upload")
	public String upload(//업로드 했을때 사진이 보이게 해야될것 같은데 어떻게 해야 할까?
			@RequestParam List<MultipartFile> files,
			@SessionAttribute(name="auth", required=false) Principal principal,
			GalleryRegistRequest dto
			) {
		
		dto.setUserId(principal.getUserId());
		galleryService.createGallery(dto, files);
		
		return "redirect:/";
	}
	
	//갤러리 리스트 화면
	@GetMapping("list")
	public String galleryList(//등록글들을 타이틀이 아닌 그림으로 보여줘야 하는데 어떻게 할까?
			@PageableDefault(size=10, sort="postIdx", direction = Direction.DESC, page = 0)
			Pageable pageable,
			Model model
			
			) {
		//갤러리 리스트를 찾을 때 UserId기준으로 찾아야하는데 어떻게 할까?
		Map<String, Object> commandMap = galleryService.findGalleryList(pageable); 
		model.addAllAttributes(commandMap);
		
		return "/gallery/gallery-list";
	}
	
	//갤러리 특정 포스트 방문시
	@GetMapping("detail")
	public String galleryDetail(Long postIdx, Model model) {
		//포스트 번호로 특정 포스트를 찾는다
		GalleryDetailResponse dto = galleryService.findGalleryByPostIdx(postIdx);
		model.addAttribute("gallery", dto);
		
		
		return "/gallery/gallery-contents";
	}
	
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
		model.addAttribute("gallery", dto);
		return "gallery/gallery-modify";
	}
	//갤러리 포스트 수정완료시
	@PostMapping("modify")
	public String modify(GalleryModifyRequest dto,
						@RequestParam List<MultipartFile> fileList,
						@SessionAttribute("auth") Principal principal
			) {
		
		dto.setUserId(principal.getUserId());
		galleryService.updateGallery(dto, fileList);
		
		return "redirect:/gallery/detail?postIdx="+dto.getPostIdx();
	}
	//갤러리 포스트 삭제시
	@PostMapping("remove")
	public String remove(Long postIdx, @SessionAttribute("auth") Principal principal) {
		
		galleryService.removeGallery(postIdx, principal);
		
		return "redirect:/gallery/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
