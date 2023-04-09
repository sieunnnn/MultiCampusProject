package multi.second.project.domain.todo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import multi.second.project.domain.planner.repository.PlannerRepository;
import multi.second.project.domain.todo.domain.AccomodationTodo;
import multi.second.project.domain.todo.domain.AttractionsTodo;
import multi.second.project.domain.todo.domain.BudgetTodo;
import multi.second.project.domain.todo.domain.GeneralTodo;
import multi.second.project.domain.todo.domain.Todo;
import multi.second.project.domain.todo.domain.TransportTodo;
import multi.second.project.domain.todo.dto.request.AccomodationTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.AccomodationTodoRegistRequest;
import multi.second.project.domain.todo.dto.request.AttractionsTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.AttractionsTodoRegistRequest;
import multi.second.project.domain.todo.dto.request.BudgetTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.BudgetTodoRegistRequest;
import multi.second.project.domain.todo.dto.request.GeneralTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.GeneralTodoRegistRequest;
import multi.second.project.domain.todo.dto.request.TodoDeleteRequest;
import multi.second.project.domain.todo.dto.request.TransportTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.TransportTodoRegistRequest;
import multi.second.project.domain.todo.dto.response.AttractionsTodoResponse;
import multi.second.project.domain.todo.dto.response.BudgetTodoResponse;
import multi.second.project.domain.todo.dto.response.GeneralTodoResponse;
import multi.second.project.domain.todo.dto.response.TransportTodoResponse;
import multi.second.project.domain.todo.repository.AccomodationRepository;
import multi.second.project.domain.todo.repository.AttractionsRepository;
import multi.second.project.domain.todo.repository.BudgetRepository;
import multi.second.project.domain.todo.repository.GeneralRepository;
import multi.second.project.domain.todo.repository.TodoRepository;
import multi.second.project.domain.todo.repository.TransportRepository;
import multi.second.project.domain.todolist.domain.TodoList;
import multi.second.project.domain.todolist.repository.TodoListRepository;
import multi.second.project.infra.code.ErrorCode;
import multi.second.project.infra.exception.HandlableException;

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
	public Todo createAccomodationTodo(AccomodationTodoRegistRequest dto, Long tpIdx, Long tlIdx) {//AccomodationTodoResponse 로 해야되나?
		//어떻게 
		Todo todo =AccomodationTodo.createAccomodationTodo(dto);//AccomodationTodo로 해야되나?
		System.out.println("todo.getTdIdx()1 : " + todo.getTdIdx());
		
		todoRepository.saveAndFlush(todo);
		
		System.out.println("todo.getTdIdx()2 : " + todo.getTdIdx());
		
//		TodoList todoList = todoListRepository.findTodoListByTodosTdIdx(todo.getTdIdx());
//		List<TodoList> todoLists = todoListRepository.findByTodosTdIdx(todo.getTdIdx());
//		System.out.println(todoList.getTitle());
//		for (TodoList todoList : todoLists) {
//			System.out.println(todoList.getTitle());
//		}
//		System.out.println("dto.getTlIdx()"+dto.getTlIdx());
		//왜 못불러오는걸까?
//		TodoList todoList1 = todoListRepository.findByTodosTdIdx(todo.getTdIdx());
//		System.out.println("todoList1.getTitle() : "+todoList1.getTitle());
//		TodoList todoList2 = todoListRepository.findTodoListByTodosTdIdx(todo.getTdIdx());
//		System.out.println("todoList2.getTitle() : "+todoList2.getTitle());
		
		
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		System.out.println(todoList.getTitle());
		
		todoList.addTodo(todo);
		todoListRepository.save(todoList);
		
		return todo;
	}
	@Transactional
	public Todo createAttractionsTodo(AttractionsTodoRegistRequest dto, Long tpIdx, Long tlIdx) {
		Todo todo =AttractionsTodo.createAttractionsTodo(dto);
		todoRepository.saveAndFlush(todo);
		
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		todoList.addTodo(todo);
		todoRepository.saveAndFlush(todo);
		
		return todo;
	}
	@Transactional
	public Todo createBudgetTodo(BudgetTodoRegistRequest dto, Long tpIdx, Long tlIdx) {
		Todo todo =BudgetTodo.createBudgetTodo(dto);
		todoRepository.saveAndFlush(todo);
		
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		todoList.addTodo(todo);
		todoRepository.saveAndFlush(todo);
		
		return todo;
	}
	@Transactional
	public Todo createGeneralTodo(GeneralTodoRegistRequest dto, Long tpIdx, Long tlIdx) {
		Todo todo =GeneralTodo.createGeneralTodo(dto);
		todoRepository.saveAndFlush(todo);
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.addTodo(todo);
		
		todoRepository.saveAndFlush(todo);
		
		return todo;
	}
	@Transactional
	public Todo createTransportTodo(TransportTodoRegistRequest dto, Long tpIdx, Long tlIdx) {
		Todo todo =TransportTodo.createTransportTodo(dto);
		todoRepository.saveAndFlush(todo);
		TodoList todoList = todoListRepository.findById(tlIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todoList.addTodo(todo);
		
		todoRepository.saveAndFlush(todo);
		
		return todo;
	}
	
	//////////////////////////////////////
	
	@Transactional
	public AccomodationTodo modifyAccomodationTodo(AccomodationTodoModifyRequest dto) {
		AccomodationTodo todo = accomodationRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateAccomodationTodo(dto);
		
		accomodationRepository.flush();
		
		return todo;
	}
	@Transactional
	public AttractionsTodo modifyAttractionsTodo(AttractionsTodoModifyRequest dto) {
		AttractionsTodo todo = attractionsRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		System.out.println("dto.getAttractions() : " +dto.getAttractions());
		todo.updateAttractionsTodo(dto);
		
		attractionsRepository.flush();
		
		return todo;
	}
	@Transactional
	public BudgetTodo modifyBudgetTodo(BudgetTodoModifyRequest dto) {
		BudgetTodo todo = budgetRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateBudgetTodo(dto);
		
		budgetRepository.flush();
		
		return todo;
	}
	@Transactional
	public GeneralTodo modifyGeneralTodo(GeneralTodoModifyRequest dto) {
		GeneralTodo todo = generalRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateGeneralTodo(dto);
		
		generalRepository.flush();
		
		return todo;
	}
	@Transactional
	public TransportTodo modifyTransportTodo(TransportTodoModifyRequest dto) {
		TransportTodo todo = transportRepository.findById(dto.getTdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		todo.updateTransportTodo(dto);
		
		transportRepository.flush();
		
		return todo;
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
	public void AccomodationDeleteTodo(TodoDeleteRequest dto) {
		
		Todo todo = accomodationRepository.findById(dto.getTdIdx())
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		System.out.println("todo.getTdIdx()"+todo.getTdIdx());
		TodoList todoList = todoListRepository.findByTodosTdIdx(dto.getTdIdx());
		System.out.println("todoList.getTlIdx() : " +todoList.getTlIdx());
		
//		TodoList todoList = todoListRepository.findById(tlIdx)
//				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
//		System.out.println("todoList.getTlIdx()"+todoList.getTlIdx());
		
		todoList.removeTodo(todo);
		accomodationRepository.delete((AccomodationTodo) todo);
		
	}
	@Transactional
	public void AttractionsDeleteTodo(TodoDeleteRequest dto) {
		
		Todo todo = attractionsRepository.findById(dto.getTdIdx())
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
//		TodoList todoList = todoListRepository.findById(tlIdx)
//				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		TodoList todoList = todoListRepository.findByTodosTdIdx(dto.getTdIdx());
		
		todoList.removeTodo(todo);
		attractionsRepository.delete((AttractionsTodo) todo);
		
	}
	@Transactional
	public void BudgetDeleteTodo(TodoDeleteRequest dto) {
		
		Todo todo = budgetRepository.findById(dto.getTdIdx())
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
//		TodoList todoList = todoListRepository.findById(tlIdx)
//				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		TodoList todoList = todoListRepository.findByTodosTdIdx(dto.getTdIdx());
		
		todoList.removeTodo(todo);
		budgetRepository.delete((BudgetTodo) todo);
		
	}
	@Transactional
	public void GeneralDeleteTodo(TodoDeleteRequest dto) {
		
		Todo todo = generalRepository.findById(dto.getTdIdx())
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
//		TodoList todoList = todoListRepository.findById(tlIdx)
//				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		TodoList todoList = todoListRepository.findByTodosTdIdx(dto.getTdIdx());
		
		todoList.removeTodo(todo);
		generalRepository.delete((GeneralTodo) todo);
		
	}
	@Transactional
	public void TransportDeleteTodo(TodoDeleteRequest dto) {
		
		Todo todo = transportRepository.findById(dto.getTdIdx())
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
//		TodoList todoList = todoListRepository.findById(tlIdx)
//				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		TodoList todoList = todoListRepository.findByTodosTdIdx(dto.getTdIdx());
		
		todoList.removeTodo(todo);
		transportRepository.delete((TransportTodo) todo);
		
	}
	







	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
