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
import multi.second.project.domain.todo.dto.request.AccomodationTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.AccomodationTodoRegistRequest;
import multi.second.project.domain.todo.dto.request.TodoModifyRequest;
import multi.second.project.domain.todo.dto.request.TodoRegistRequest;
import multi.second.project.domain.todo.dto.response.AccomodationTodoResponse;
import multi.second.project.domain.todo.dto.response.AttractionsTodoResponse;
import multi.second.project.domain.todo.dto.response.BudgetTodoResponse;
import multi.second.project.domain.todo.dto.response.GeneralTodoResponse;
import multi.second.project.domain.todo.dto.response.TodoResponse;
import multi.second.project.domain.todo.dto.response.TransportTodoResponse;
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
	public AccomodationTodoResponse createAccomodationTodo(AccomodationTodoRegistRequest dto, Long tpIdx) {//AccomodationTodoResponse 로 해야되나?
		//어떻게 
		AccomodationTodo todo =AccomodationTodo.createAccomodationTodo(dto);//AccomodationTodo로 해야되나?
		
		TodoList todoList = todoListRepository.findByTodosTdIdx(todo.getTdIdx());
		
		todoList.accomodationAddTodo(todo);
		
		todoRepository.saveAndFlush(todo);
		
		return new AccomodationTodoResponse(todo);
	}
	@Transactional
	public AttractionsTodoResponse createAttractionsTodo(TodoRegistRequest dto, Long tpIdx, Long tlIdx) {
		AttractionsTodo todo =AttractionsTodo.createAttractionsTodo(dto);

		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.attractionsAddTodo(todo);
		
		todoRepository.saveAndFlush(todo);
		
		return new AttractionsTodoResponse(todo);
	}
	@Transactional
	public BudgetTodoResponse createBudgetTodo(TodoRegistRequest dto, Long tpIdx, Long tlIdx) {
		BudgetTodo todo =BudgetTodo.createBudgetTodo(dto);

		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.budgetAddTodo(todo);
		
		todoRepository.saveAndFlush(todo);
		
		return new BudgetTodoResponse(todo);
	}
	@Transactional
	public GeneralTodoResponse createGeneralTodo(TodoRegistRequest dto, Long tpIdx, Long tlIdx) {
		GeneralTodo todo =GeneralTodo.createGeneralTodo(dto);

		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.generalAddTodo(todo);
		
		todoRepository.saveAndFlush(todo);
		
		return new GeneralTodoResponse(todo);
	}
	@Transactional
	public TransportTodoResponse createTransportTodo(TodoRegistRequest dto, Long tpIdx, Long tlIdx) {
		TransportTodo todo =TransportTodo.createTransportTodo(dto);

		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.transportAddTodo(todo);
		
		todoRepository.saveAndFlush(todo);
		
		return new TransportTodoResponse(todo);
	}
	
	//////////////////////////////////////
	
	@Transactional
	public AccomodationTodoResponse modifyAccomodationTodo(AccomodationTodoModifyRequest dto) {
		AccomodationTodo todo = accomodationRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateAccomodationTodo(dto);
		
		accomodationRepository.flush();
		
		return new AccomodationTodoResponse(todo);
	}
	@Transactional
	public AttractionsTodoResponse modifyAttractionsTodo(TodoModifyRequest dto) {
		AttractionsTodo todo = attractionsRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateAttractionsTodo(dto);
		
		attractionsRepository.flush();
		
		return new AttractionsTodoResponse(todo);
	}
	@Transactional
	public BudgetTodoResponse modifyBudgetTodo(TodoModifyRequest dto) {
		BudgetTodo todo = budgetRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateBudgetTodo(dto);
		
		budgetRepository.flush();
		
		return new BudgetTodoResponse(todo);
	}
	@Transactional
	public GeneralTodoResponse modifyGeneralTodo(TodoModifyRequest dto) {
		GeneralTodo todo = generalRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateGeneralTodo(dto);
		
		generalRepository.flush();
		
		return new GeneralTodoResponse(todo);
	}
	@Transactional
	public TransportTodoResponse modifyTransportTodo(TodoModifyRequest dto) {
		TransportTodo todo = transportRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateTransportTodo(dto);
		
		transportRepository.flush();
		
		return new TransportTodoResponse(todo);
	}

	////////////////////


	
//	@Transactional
//	public Boolean deleteTodo(Long tdIdx, Long tlIdx) {
//		
//		Todo todo = todoRepository.findById(tdIdx)
//				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
//		
//		TodoList todoList = todoListRepository.findById(tlIdx)
//				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
//		
//		todoList.removeTodo(todo);
//		todoRepository.delete(todo);
//		
//		return true;
//	}
	@Transactional
	public Boolean AccomodationDeleteTodo(Long tdIdx, Long tlIdx) {
		
		AccomodationTodo todo = accomodationRepository.findById(tdIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.accomodationRemoveTodo(todo);
		accomodationRepository.delete(todo);
		
		return true;
	}
	@Transactional
	public Boolean AttractionsDeleteTodo(Long tdIdx, Long tlIdx) {
		
		AttractionsTodo todo = attractionsRepository.findById(tdIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.attractionsTodoRemoveTodo(todo);
		attractionsRepository.delete(todo);
		
		return true;
	}
	@Transactional
	public Boolean BudgetDeleteTodo(Long tdIdx, Long tlIdx) {
		
		BudgetTodo todo = budgetRepository.findById(tdIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.budgetTodoRemoveTodo(todo);
		budgetRepository.delete(todo);
		
		return true;
	}
	@Transactional
	public Boolean GeneralDeleteTodo(Long tdIdx, Long tlIdx) {
		
		GeneralTodo todo = generalRepository.findById(tdIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.generalTodoRemoveTodo(todo);
		generalRepository.delete(todo);
		
		return true;
	}
	@Transactional
	public Boolean TransportDeleteTodo(Long tdIdx, Long tlIdx) {
		
		TransportTodo todo = transportRepository.findById(tdIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.transportTodoRemoveTodo(todo);
		transportRepository.delete(todo);
		
		return true;
	}
	







	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
