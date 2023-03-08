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
@RequestMapping("board")
public class GalleryController {

	private final GalleryService galleryService;
	
	@GetMapping("form")
	public String boardForm() {
		return "/board/board-form";
	}
	
	@PostMapping("upload")
	public String upload(
			@RequestParam List<MultipartFile> files,
			@SessionAttribute(name="auth", required=false) Principal principal,
			GalleryRegistRequest dto
			) {
		
		dto.setUserId(principal.getUserId());
		galleryService.createGallery(dto, files);
		
		return "redirect:/";
	}
	
	@GetMapping("list")
	public String boardList(
			@PageableDefault(size=10, sort="psotIdx", direction = Direction.DESC, page = 0)
			Pageable pageable,
			Model model
			
			) {

		Map<String, Object> commandMap = galleryService.findGalleryList(pageable); 
		model.addAllAttributes(commandMap);
		
		return "/board/board-list";
	}
	
	@GetMapping("detail")
	public String boardDetail(Long postIdx, Model model) {
		
		GalleryDetailResponse dto = galleryService.findGalleryByPostIdx(postIdx);
		model.addAttribute("board", dto);
		
		return "/board/board-contents";
	}
	
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
	
	
	@GetMapping("modify")
	public String boardModify(Long postIdx, Model model) {
		GalleryDetailResponse dto = galleryService.findGalleryByPostIdx(postIdx);
		model.addAttribute("board", dto);
		return "board/board-modify";
	}
	
	@PostMapping("modify")
	public String modify(GalleryModifyRequest dto,
						@RequestParam List<MultipartFile> fileList,
						@SessionAttribute("auth") Principal principal
			) {
		
		dto.setUserId(principal.getUserId());
		galleryService.updateGallery(dto, fileList);
		
		return "redirect:/board/detail?postIdx="+dto.getPostIdx();
	}
	
	@PostMapping("remove")
	public String remove(Long bdIdx, @SessionAttribute("auth") Principal principal) {
		
		galleryService.removeGallery(bdIdx, principal);
		
		return "redirect:/board/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
