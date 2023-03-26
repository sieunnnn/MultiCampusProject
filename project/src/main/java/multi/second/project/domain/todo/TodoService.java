package multi.second.project.domain.todo;

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
import multi.second.project.domain.member.MemberRepository;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.member.dto.Principal;
import multi.second.project.domain.planner.domain.Planner;
import multi.second.project.domain.planner.repository.PlannerRepository;
import multi.second.project.domain.todo.domain.Todo;
import multi.second.project.domain.todo.dto.request.TodoRegistRequest;
import multi.second.project.domain.todo.repository.TodoRepository;
import multi.second.project.domain.todolist.domain.TodoList;
import multi.second.project.domain.todolist.dto.request.TodoListModifyRequest;
import multi.second.project.domain.todolist.dto.request.TodoListRegistRequest;
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
public class TodoService {
	
	private final PlannerRepository plannerRepository;
	private final TodoListRepository todoListRepository;
	private final TodoRepository todoRepository;

	
	@Transactional
	public void deleteTodoList(Long tlIdx, Long tpIdx) {
		
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		Planner planner = plannerRepository.findById(tpIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		
		
		
		planner.removeTodoList(todoList);
		todoListRepository.delete(todoList);
		
	}
	
	
	@Transactional
	public void createTodoList(TodoListRegistRequest dto, Long tpIdx) {
		
		TodoList todoList =TodoList.createTodoList(dto);
		
		Planner planner = plannerRepository.findById(tpIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		planner.addTodoList(todoList);
		todoListRepository.saveAndFlush(todoList);
	}
	
	@Transactional
	public void createTodo(TodoRegistRequest dto, Long tpIdx, Long tlIdx) {
		
//		Todo todo = Todo.createTodo(dto);
		
	}
	
	
	@Transactional
	public void updateTodoList(TodoListModifyRequest dto) {
		
		TodoList todoList = todoListRepository.findById(dto.getTlIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.updateTodoList(dto);
		
		todoListRepository.flush();
		
	}







	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
