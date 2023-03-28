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
import multi.second.project.domain.todo.domain.AccomodationTodo;
import multi.second.project.domain.todo.domain.AttractionsTodo;
import multi.second.project.domain.todo.domain.BudgetTodo;
import multi.second.project.domain.todo.domain.GeneralTodo;
import multi.second.project.domain.todo.domain.Todo;
import multi.second.project.domain.todo.domain.TransportTodo;
import multi.second.project.domain.todo.dto.request.TodoModifyRequest;
import multi.second.project.domain.todo.dto.request.TodoRegistRequest;
import multi.second.project.domain.todo.dto.response.AccomodationTodoResponse;
import multi.second.project.domain.todo.dto.response.TodoResponse;
import multi.second.project.domain.todo.repository.AccomodationRepository;
import multi.second.project.domain.todo.repository.AttractionsRepository;
import multi.second.project.domain.todo.repository.BudgetRepository;
import multi.second.project.domain.todo.repository.GeneralRepository;
import multi.second.project.domain.todo.repository.TodoRepository;
import multi.second.project.domain.todo.repository.TransportRepository;
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
	
	private final AccomodationRepository accomodationRepository;
	private final AttractionsRepository attractionsRepository;
	private final BudgetRepository budgetRepository;
	private final GeneralRepository generalRepository;
	private final TransportRepository transportRepository;

	@Transactional
	public TodoResponse createAccomodationTodo(TodoRegistRequest dto, Long tpIdx, Long tlIdx) {//AccomodationTodoResponse 로 해야되나?
		//어떻게 
		Todo todo =Todo.createAccomodationTodo(dto);//AccomodationTodo로 해야되나?
		
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.addTodo(todo);
		
		todoRepository.saveAndFlush(todo);
		
		return new TodoResponse(todo);
	}
	@Transactional
	public TodoResponse createAttractionsTodo(TodoRegistRequest dto, Long tpIdx, Long tlIdx) {
		Todo todo =Todo.createAttractionsTodo(dto);

		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.addTodo(todo);
		
		todoRepository.saveAndFlush(todo);
		
		return new TodoResponse(todo);
	}
	@Transactional
	public TodoResponse createBudgetTodo(TodoRegistRequest dto, Long tpIdx, Long tlIdx) {
		Todo todo =Todo.createBudgetTodo(dto);

		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.addTodo(todo);
		
		todoRepository.saveAndFlush(todo);
		
		return new TodoResponse(todo);
	}
	@Transactional
	public TodoResponse createGeneralTodo(TodoRegistRequest dto, Long tpIdx, Long tlIdx) {
		Todo todo =Todo.createGeneralTodo(dto);

		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.addTodo(todo);
		
		todoRepository.saveAndFlush(todo);
		
		return new TodoResponse(todo);
	}
	@Transactional
	public TodoResponse createTransportTodo(TodoRegistRequest dto, Long tpIdx, Long tlIdx) {
		Todo todo =Todo.createTransportTodo(dto);

		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.addTodo(todo);
		
		todoRepository.saveAndFlush(todo);
		
		return new TodoResponse(todo);
	}
	
	//////////////////////////////////////
	
	@Transactional
	public TodoResponse modifyAccomodationTodo(TodoModifyRequest dto) {
		AccomodationTodo todo = accomodationRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateAccomodationTodo(dto);
		
		accomodationRepository.flush();
		
		return new TodoResponse(todo);
	}
	@Transactional
	public TodoResponse modifyAttractionsTodo(TodoModifyRequest dto) {
		AttractionsTodo todo = attractionsRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateAttractionsTodo(dto);
		
		attractionsRepository.flush();
		
		return new TodoResponse(todo);
	}
	@Transactional
	public TodoResponse modifyBudgetTodo(TodoModifyRequest dto) {
		BudgetTodo todo = budgetRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateBudgetTodo(dto);
		
		budgetRepository.flush();
		
		return new TodoResponse(todo);
	}
	@Transactional
	public TodoResponse modifyGeneralTodo(TodoModifyRequest dto) {
		GeneralTodo todo = generalRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateGeneralTodo(dto);
		
		generalRepository.flush();
		
		return new TodoResponse(todo);
	}
	@Transactional
	public TodoResponse modifyTransportTodo(TodoModifyRequest dto) {
		TransportTodo todo = transportRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateTransportTodo(dto);
		
		transportRepository.flush();
		
		return new TodoResponse(todo);
	}

	////////////////////


	
	@Transactional
	public Boolean deleteTodo(Long tdIdx, Long tlIdx) {
		
		Todo todo = todoRepository.findById(tdIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.removeTodo(todo);
		todoRepository.delete(todo);
		
		return true;
	}
	







	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
