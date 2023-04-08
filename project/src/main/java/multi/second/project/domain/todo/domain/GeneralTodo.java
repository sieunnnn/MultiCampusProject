package multi.second.project.domain.todo.domain;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import multi.second.project.domain.todo.dto.request.GeneralTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.GeneralTodoRegistRequest;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@SuperBuilder @NoArgsConstructor @AllArgsConstructor @Getter
public class GeneralTodo extends Todo{

	
	//주소
	private String address;
//	@Id
//	@GeneratedValue
//	private Long gtIdx;
	
	public static GeneralTodo createGeneralTodo(GeneralTodoRegistRequest dto) {
		return GeneralTodo.builder()
				.contents(dto.getContents())
				.title(dto.getTitle())
				.todoDate(dto.getTodoDate())
				.isPrivate(dto.getIsPrivate())
				.todoType(TodoType.General)
				.address(dto.getAddress())
				.build();
	}

	public void updateGeneralTodo(GeneralTodoModifyRequest dto) {
		this.title = dto.getTitle();
		this.contents = dto.getContents();
		this.todoDate = dto.getTodoDate();
		this.isPrivate = dto.getIsPrivate();
		this.address = dto.getAddress();
		
	}
	
//	//투두카드 1번
//	@ManyToOne
//	@JoinColumn(name = "todoType")
//	private Todo todo;
	
//	//일정날짜
//	@Column(columnDefinition = "timestamp default now()")
//	private LocalDateTime todoDate;
	
//	//제목
//	private String title;
//	//내용
//	private String contents;

//	//공개여부
//	@ColumnDefault("false")
//	private Boolean isPrivate;
}
