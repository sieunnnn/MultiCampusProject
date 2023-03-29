package multi.second.project.domain.todo.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.comment.domain.Comment;
import multi.second.project.domain.todo.domain.Todo;
import multi.second.project.domain.todo.domain.TransportTodo;
import multi.second.project.domain.todo.domain.TransportType;
import multi.second.project.domain.todolist.domain.TodoList;

@Data
@NoArgsConstructor
public class TransportTodoResponse {

	private Long tdIdx;
	private String title;
	private String contents;
	private LocalDateTime regDate;
	private LocalDateTime todoDate;
	private Boolean isPrivate;
	private String time;
	private TransportType transportType;

	public TransportTodoResponse(TransportTodo entity) {
		this.tdIdx = entity.getTdIdx();
		this.title = entity.getTitle();
		this.contents = entity.getContents();
		this.regDate = entity.getRegDate();
		this.todoDate = entity.getTodoDate();
		this.isPrivate = entity.getIsPrivate();
		this.time = entity.getTime();
		this.transportType = entity.getTransportType();
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
	
	public static List<TransportTodoResponse> toDtoList(List<TransportTodo> entityList){
		return entityList.stream().map(e -> new TransportTodoResponse(e)).collect(Collectors.toList());
	}

}
