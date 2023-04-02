package multi.second.project.domain.todolist.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.comment.domain.Comment;
import multi.second.project.domain.todo.dto.response.AccomodationTodoResponse;
import multi.second.project.domain.todo.dto.response.AttractionsTodoResponse;
import multi.second.project.domain.todo.dto.response.BudgetTodoResponse;
import multi.second.project.domain.todo.dto.response.GeneralTodoResponse;
import multi.second.project.domain.todo.dto.response.TodoResponse;
import multi.second.project.domain.todo.dto.response.TransportTodoResponse;
import multi.second.project.domain.todolist.domain.TodoList;

@Data
@NoArgsConstructor
public class TodoListResponse {

	private Long tlIdx;
	private String title;
//	private LocalDateTime regDate;
	private List<TodoResponse> todoResponses = new ArrayList<TodoResponse>();
	
	private List<AccomodationTodoResponse> accomodationTodoResponses = new ArrayList<AccomodationTodoResponse>();
	private List<AttractionsTodoResponse> attractionsTodoResponses = new ArrayList<AttractionsTodoResponse>();
	private List<BudgetTodoResponse> budgetTodoResponses = new ArrayList<BudgetTodoResponse>();
	private List<GeneralTodoResponse> generalTodoResponses = new ArrayList<GeneralTodoResponse>();
	private List<TransportTodoResponse> transportTodoResponses = new ArrayList<TransportTodoResponse>();

	public TodoListResponse(TodoList entity) {
		this.tlIdx = entity.getTlIdx();
		this.title = entity.getTitle();
//		this.regDate = entity.getRegDate();
		this.todoResponses = TodoResponse.toDtoList(entity.getTodos());
		
		this.accomodationTodoResponses = AccomodationTodoResponse.toDtoList(entity.getAccomodationTodos());
		this.attractionsTodoResponses = AttractionsTodoResponse.toDtoList(entity.getAttractionsTodos());
		this.budgetTodoResponses = BudgetTodoResponse.toDtoList(entity.getBudgetTodos());
		this.generalTodoResponses = GeneralTodoResponse.toDtoList(entity.getGeneralTodos());
		this.transportTodoResponses = TransportTodoResponse.toDtoList(entity.getTransportTodos());
	}
	
//	public String getRegDateAsDate() {
//		return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//	}
//	
//	public String getRegDateAsTime() {
//		return regDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
//	}
	
	public static List<TodoListResponse> toDtoList(List<TodoList> entityList){
		return entityList.stream().map(e -> new TodoListResponse(e)).collect(Collectors.toList());
	}

}
