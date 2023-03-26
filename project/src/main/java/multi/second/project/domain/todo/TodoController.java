package multi.second.project.domain.todo;

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
import multi.second.project.domain.todo.dto.request.TodoRegistRequest;
import multi.second.project.domain.todolist.dto.request.TodoListModifyRequest;
import multi.second.project.domain.todolist.dto.request.TodoListRegistRequest;
import multi.second.project.infra.util.file.dto.FilePathDto;

@Controller
@AllArgsConstructor
@RequestMapping("todo")
public class TodoController {
	
	private final TodoService todoService;
//
//	@MessageMapping("upload-accomodation/{tgIdx}")
//	public String uploadAccomodation(
//			@DestinationVariable("tgIdx") Long tgIdx,
//			TodoRegistRequest dto,
//			Long tpIdx,
//			Long tlIdx
//			) {
//		
//		todoService.createTodo(dto, tpIdx, tlIdx);
//		
//		return "redirect:/planner/detail?tpIdx="+tpIdx;
//	}
//	
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
//	
//	@PostMapping("remove")
//	public String remove(
//			Long tlIdx, 
//			Long tpIdx
//			) {
//		
//		todoListService.deleteTodoList(tlIdx, tpIdx);
//		
//		return "redirect:/gallery/detail?postIdx="+tpIdx;
//	}
//	
//	
	
	
}
