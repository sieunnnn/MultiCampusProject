package multi.second.project.domain.todolist.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multi.second.project.domain.comment.dto.request.CommentModifyRequest;
import multi.second.project.domain.todo.domain.AccomodationTodo;
import multi.second.project.domain.todo.domain.AttractionsTodo;
import multi.second.project.domain.todo.domain.BudgetTodo;
import multi.second.project.domain.todo.domain.GeneralTodo;
import multi.second.project.domain.todo.domain.Todo;
import multi.second.project.domain.todo.domain.TransportTodo;
import multi.second.project.domain.todolist.dto.request.TodoListModifyRequest;
import multi.second.project.domain.todolist.dto.request.TodoListRegistRequest;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class TodoList {

	@Id
	@GeneratedValue
	private Long tlIdx;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<Todo> todos = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<AccomodationTodo> accomodationTodos = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<AttractionsTodo> attractionsTodos = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<BudgetTodo> budgetTodos = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<GeneralTodo> generalTodos = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<TransportTodo> transportTodos = new ArrayList<>();
	
	
	
//	//플래너 번호
//	@ManyToOne
//	@JoinColumn(name = "tpIdx")
//	private Planner planner;
	
	//투두리스트 생성 시간
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	//투두리스트 제목
	private String title;
	
	//투두리스트 삭제 여부
	@ColumnDefault("false")
	private Boolean isDel;
	
	

	public static TodoList createTodoList(TodoListRegistRequest dto) {
		return TodoList.builder()
				.title(dto.getTitle()).build();
	}
	
	public void updateTodoList(TodoListModifyRequest dto) {
		this.title = dto.getTitle();
	}

	///////////////////////////////
	public void addTodo(Todo todo) {
		todos.add(todo);
	}
	public void accomodationAddTodo(AccomodationTodo todo) {
		accomodationTodos.add(todo);
	}
	public void attractionsAddTodo(AttractionsTodo todo) {
		attractionsTodos.add(todo);
	}
	public void budgetAddTodo(BudgetTodo todo) {
		budgetTodos.add(todo);
	}
	public void generalAddTodo(GeneralTodo todo) {
		generalTodos.add(todo);
	}
	public void transportAddTodo(TransportTodo todo) {
		transportTodos.add(todo);
	}
	//////////////////////////////
	
	public void removeTodo(Todo todo) {
		todos.remove(todo);
	}
	public void accomodationRemoveTodo(AccomodationTodo todo) {
		accomodationTodos.remove(todo);
	}
	public void attractionsTodoRemoveTodo(AttractionsTodo todo) {
		attractionsTodos.remove(todo);
	}
	public void budgetTodoRemoveTodo(BudgetTodo todo) {
		budgetTodos.remove(todo);
	}
	public void generalTodoRemoveTodo(GeneralTodo todo) {
		generalTodos.remove(todo);
	}
	public void transportTodoRemoveTodo(TransportTodo todo) {
		transportTodos.remove(todo);
	}
	
	
	

}
