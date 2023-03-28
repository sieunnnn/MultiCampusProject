package multi.second.project.domain.todolist;

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
import multi.second.project.domain.comment.dto.request.CommentModifyRequest;
import multi.second.project.domain.comment.dto.request.CommentRegistRequest;
import multi.second.project.domain.gallery.dto.request.GalleryModifyRequest;
import multi.second.project.domain.gallery.dto.request.GalleryRegistRequest;
import multi.second.project.domain.gallery.dto.response.GalleryDetailResponse;
import multi.second.project.domain.member.UserPrincipal;
import multi.second.project.domain.member.dto.Principal;
import multi.second.project.domain.todolist.dto.request.TodoListModifyRequest;
import multi.second.project.domain.todolist.dto.request.TodoListRegistRequest;
import multi.second.project.infra.util.file.dto.FilePathDto;

@Controller
@AllArgsConstructor
@RequestMapping("todolist")
public class TodoListController {
	
	private final TodoListService todoListService;
	private final SimpMessagingTemplate simpMessagingTemplate;

//	@PostMapping("upload")
//	public String upload(
//			TodoListRegistRequest dto,
//			Long tpIdx
//			) {
//		
//		todoListService.createTodoList(dto, tpIdx);
//		
//		return "redirect:/planner/detail?tpIdx="+tpIdx;
//	}
	
	@MessageMapping("/upload-todolist/{tpIdx}")
	public void upload(
			@DestinationVariable("tpIdx") Long tpIdx,
			TodoListRegistRequest dto
			) throws Exception {
		simpMessagingTemplate.convertAndSend("/topic/upload-todolist/" + tpIdx, todoListService.createTodoList(dto, tpIdx));
		
	}
	
//	@PostMapping("modify")
//	public String modify(
//			TodoListModifyRequest dto,
//			Long tpIdx
//			) {
//
//		todoListService.updateTodoList(dto);
//		
//		return "redirect:/planner/detail?tpIdx="+tpIdx;
//	}
	
	//삭제나 업데이트는 어떻게...?
	//삭제는 리퀘스트에 isdel 바꾸는거 넣어서?
	
	@MessageMapping("modify-todolist/{tpIdx}")
	public void modify(
			@DestinationVariable("tpIdx") Long tpIdx,
			TodoListModifyRequest dto
			) throws Exception {

		simpMessagingTemplate.convertAndSend("/topic/modify-todolist/" + tpIdx, todoListService.updateTodoList(dto));
		
	}
	
//	@PostMapping("remove")
//	public String remove(
//			Long tlIdx, 
//			Long tpIdx
//			) {
//		
//		todoListService.deleteTodoList(tlIdx, tpIdx);
//		return "redirect:/gallery/detail?postIdx="+tpIdx;
//	}
	
	@MessageMapping("remove-todolist/{tpIdx}")
	public void remove( 
			@DestinationVariable("tpIdx") Long tpIdx,
			Long tlIdx
			) {
		simpMessagingTemplate.convertAndSend("/topic/remove-todolist/" + tpIdx, todoListService.deleteTodoList(tlIdx, tpIdx));
		
	}
	
	
	
	
}
