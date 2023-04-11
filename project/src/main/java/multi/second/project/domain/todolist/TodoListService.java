package multi.second.project.domain.todolist;

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
import multi.second.project.domain.comment.dto.request.CommentModifyRequest;
import multi.second.project.domain.comment.dto.request.CommentRegistRequest;
import multi.second.project.domain.comment.dto.response.CommentResponse;
import multi.second.project.domain.comment.repository.CommentRepository;
import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.gallery.dto.request.GalleryModifyRequest;
import multi.second.project.domain.gallery.dto.request.GalleryRegistRequest;
import multi.second.project.domain.gallery.dto.response.GalleryDetailResponse;
import multi.second.project.domain.gallery.dto.response.GalleryListResponse;
import multi.second.project.domain.gallery.repository.GalleryRepository;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.member.dto.Principal;
import multi.second.project.domain.member.repository.MemberRepository;
import multi.second.project.domain.planner.domain.Planner;
import multi.second.project.domain.planner.dto.response.PlannerDetailResponse;
import multi.second.project.domain.planner.repository.PlannerRepository;
import multi.second.project.domain.todolist.domain.TodoList;
import multi.second.project.domain.todolist.dto.request.TodoListModifyRequest;
import multi.second.project.domain.todolist.dto.request.TodoListRegistRequest;
import multi.second.project.domain.todolist.dto.response.TodoListResponse;
import multi.second.project.domain.todolist.repository.TodoListRepository;
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
public class TodoListService {

	private final PlannerRepository plannerRepository;
	private final TodoListRepository todoListRepository;

	//postmapping했을때
//	@Transactional
//	public void createTodoList(TodoListRegistRequest dto, Long tpIdx) {
//		
//		TodoList todoList =TodoList.createTodoList(dto);
//		
//		Planner planner = plannerRepository.findById(tpIdx)
//				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
//		
//		planner.addTodoList(todoList);
//		todoListRepository.saveAndFlush(todoList);
//		
//	}
	
	//투두리스트 추가
	@Transactional
	public TodoListResponse createTodoList(TodoListRegistRequest dto, Long tpIdx) {
		
		TodoList todoList =TodoList.createTodoList(dto);
		
		Planner planner = plannerRepository.findById(tpIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		planner.addTodoList(todoList);
		todoListRepository.saveAndFlush(todoList);
	
		//TodoListResponse res = new TodoListResponse();
		return new TodoListResponse(todoList);//굳이 response에 담을 필요없이 이렇게 받아도 되지 않나?
	}
	
	//투두리스트 수정
	@Transactional
	public TodoListResponse updateTodoList(TodoListModifyRequest dto) {
		
		TodoList todoList = todoListRepository.findById(dto.getTlIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.updateTodoList(dto);
		
		todoListRepository.flush();
		
		return new TodoListResponse(todoList);//업데이트인데 뭘보내야하지..?
	}
	
	//투두리스트 삭제
	@Transactional
	public void deleteTodoList(Long tlIdx, Long tpIdx) {//삭제는 뭘보내야하지?
		
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		Planner planner = plannerRepository.findById(tpIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		planner.removeTodoList(todoList);
		todoListRepository.delete(todoList);
		
	}
	
	






	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
