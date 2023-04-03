package multi.second.project.domain.todo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import multi.second.project.domain.todo.dto.request.AccomodationTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.AccomodationTodoRegistRequest;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@SuperBuilder
@NoArgsConstructor @AllArgsConstructor @Getter
public class AccomodationTodo extends Todo{


	//투두카드 여행 주소
	private String address;
	
//	//숙박일자
//	@Column(columnDefinition = "timestamp default now()")
//	private LocalDateTime todoDate;
	
//	//투두카드 제목
//	private String title;
//	//투두카드 내용
//	private String contents;
	
//	//투두카드 공개여부
//	@ColumnDefault("false")
//	private Boolean isPrivate;
	
//	//투두카드 4번
//	@ManyToOne
//	@JoinColumn(name = "todoType")
//	private Todo todo;
	public static AccomodationTodo createAccomodationTodo(AccomodationTodoRegistRequest dto) {//리퀘스트는 하나로 해도 되나?
		
		return AccomodationTodo.builder()
				.contents(dto.getContents())
				.title(dto.getTitle())
				.todoDate(dto.getTodoDate())
				.isPrivate(dto.getIsPrivate())
				.todoType(TodoType.Accomodation)
				.address(dto.getAddress())
				.build();
	}
	
	public void updateAccomodationTodo(AccomodationTodoModifyRequest dto) {
		this.title = dto.getTitle();
		this.contents = dto.getContents();
		this.todoDate = dto.getTodoDate();
		this.isPrivate = dto.getIsPrivate();
		this.address = dto.getAddress();
	}
}
