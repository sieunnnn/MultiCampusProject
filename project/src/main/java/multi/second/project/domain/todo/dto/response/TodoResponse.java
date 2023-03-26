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
	
	private String acAddress;
	
	private String attractions;
	
	private BudgetType budgetType;
	private Integer budget;
	
	private String gnAddress;
	
	private String time;
	private TransportType transportType;
	
	
	public TodoResponse(Todo entity) {
		this.tdIdx = entity.getTdIdx();
		this.title = entity.getTitle();
		this.contents = entity.getContents();
		this.regDate = entity.getRegDate();
		this.todoDate = entity.getTodoDate();
		this.isPrivate = entity.getIsPrivate();
		this.acAddress = ((AccomodationTodo) entity).getAddress();
		this.attractions = ((AttractionsTodo) entity).getAttractions();
		this.budgetType = ((BudgetTodo) entity).getBudgetType();
		this.budget = ((BudgetTodo) entity).getBudget();
		this.gnAddress = ((GeneralTodo) entity).getAddress();
		this.time = ((TransportTodo) entity).getTime();
		this.transportType = ((TransportTodo) entity).getTransportType();
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
		return entityList.stream().map(e -> new TodoResponse(e)).collect(Collectors.toList());
	}

}
