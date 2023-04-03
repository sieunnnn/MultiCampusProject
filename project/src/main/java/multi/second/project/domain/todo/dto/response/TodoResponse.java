package multi.second.project.domain.todo.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.comment.domain.Comment;
import multi.second.project.domain.todo.domain.AccomodationTodo;
import multi.second.project.domain.todo.domain.AttractionsTodo;
import multi.second.project.domain.todo.domain.BudgetTodo;
import multi.second.project.domain.todo.domain.BudgetType;
import multi.second.project.domain.todo.domain.GeneralTodo;
import multi.second.project.domain.todo.domain.Todo;
import multi.second.project.domain.todo.domain.TodoType;
import multi.second.project.domain.todo.domain.TransportTodo;
import multi.second.project.domain.todo.domain.TransportType;
import multi.second.project.domain.todolist.domain.TodoList;

@Data
@NoArgsConstructor
public class TodoResponse {

	private Long tdIdx;
	private String title;
	private String contents;
	private LocalDateTime regDate;
	private LocalDateTime todoDate;
	private Boolean isPrivate;
	private TodoType todoType;
	
	private String acAddress;
	
	private String attractions;
	
	private BudgetType budgetType;
	private Integer budget;
	
	private String gnAddress;
	
	private String time;
	private TransportType transportType;
	
	
	private TodoResponse(AccomodationTodo entity) {
		this.tdIdx = entity.getTdIdx();
		this.title = entity.getTitle();
		this.contents = entity.getContents();
		this.regDate = entity.getRegDate();
		this.todoDate = entity.getTodoDate();
		this.isPrivate = entity.getIsPrivate();
		this.todoType = entity.getTodoType();
		this.acAddress = entity.getAddress();
	}
	
	private TodoResponse(AttractionsTodo entity) {
		this.tdIdx = entity.getTdIdx();
		this.title = entity.getTitle();
		this.contents = entity.getContents();
		this.regDate = entity.getRegDate();
		this.todoDate = entity.getTodoDate();
		this.isPrivate = entity.getIsPrivate();
		this.todoType = entity.getTodoType();
		this.attractions = entity.getAttractions();
	}
	
	private TodoResponse(BudgetTodo entity) {
		this.tdIdx = entity.getTdIdx();
		this.title = entity.getTitle();
		this.contents = entity.getContents();
		this.regDate = entity.getRegDate();
		this.todoDate = entity.getTodoDate();
		this.isPrivate = entity.getIsPrivate();
		this.todoType = entity.getTodoType();
		this.budgetType = entity.getBudgetType();
		this.budget = entity.getBudget();
	}
	
	private TodoResponse(GeneralTodo entity) {
		this.tdIdx = entity.getTdIdx();
		this.title = entity.getTitle();
		this.contents = entity.getContents();
		this.regDate = entity.getRegDate();
		this.todoDate = entity.getTodoDate();
		this.isPrivate = entity.getIsPrivate();
		this.todoType = entity.getTodoType();
		this.gnAddress = entity.getAddress();
	}
	
	private TodoResponse(TransportTodo entity) {
		this.tdIdx = entity.getTdIdx();
		this.title = entity.getTitle();
		this.contents = entity.getContents();
		this.regDate = entity.getRegDate();
		this.todoDate = entity.getTodoDate();
		this.isPrivate = entity.getIsPrivate();
		this.todoType = entity.getTodoType();
		this.time = entity.getTime();
		this.transportType = entity.getTransportType();
	}
	
	public static TodoResponse createTodoResponse(Todo todo) {
		if(todo instanceof AccomodationTodo) {
			return new TodoResponse((AccomodationTodo) todo);
		}else if(todo instanceof AttractionsTodo) {
			return new TodoResponse((AttractionsTodo) todo);
		}else if(todo instanceof BudgetTodo) {
			return new TodoResponse((BudgetTodo) todo);
		}else if(todo instanceof GeneralTodo) {
			return new TodoResponse((GeneralTodo) todo);
		}else if(todo instanceof TransportTodo) {
			return new TodoResponse((TransportTodo) todo);
		}
		return null;
	}
	
	public String getRegDateAsDate() {
		return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getRegDateAsTime() {
		return regDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	public String getTodoDateAsDate() {
		return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getTodoDateAsTime() {
		return regDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	public static List<TodoResponse> toDtoList(List<Todo> entityList){
		return entityList.stream().map(e -> createTodoResponse(e)).collect(Collectors.toList());
	}
//	
//	
//	public static List<TodoResponse> toDtoListAccomodation(List<AccomodationTodo> entityList){
//		return entityList.stream().map(e -> new TodoResponse(e)).collect(Collectors.toList());
//	}
//	public static List<TodoResponse> toDtoListAttractions(List<AttractionsTodo> entityList){
//		return entityList.stream().map(e -> new TodoResponse(e)).collect(Collectors.toList());
//	}
//	public static List<TodoResponse> toDtoListBudget(List<BudgetTodo> entityList){
//		return entityList.stream().map(e -> new TodoResponse(e)).collect(Collectors.toList());
//	}
//	public static List<TodoResponse> toDtoListGeneral(List<GeneralTodo> entityList){
//		return entityList.stream().map(e -> new TodoResponse(e)).collect(Collectors.toList());
//	}
//	public static List<TodoResponse> toDtoListTransport(List<TransportTodo> entityList){
//		return entityList.stream().map(e -> new TodoResponse(e)).collect(Collectors.toList());
//	}

}
