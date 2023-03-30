package multi.second.project.domain.todo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import multi.second.project.domain.todo.dto.request.TodoModifyRequest;
import multi.second.project.domain.todo.dto.request.TodoRegistRequest;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@SuperBuilder @NoArgsConstructor @AllArgsConstructor @Getter
public class TransportTodo extends Todo {

	//교통수단 시간
	private String time;

	@Enumerated(EnumType.STRING)
	private TransportType transportType;
	
	public static TransportTodo createTransportTodo(TodoRegistRequest dto) {
		return TransportTodo.builder()
				.contents(dto.getContents())
				.title(dto.getTitle())
				.todoDate(dto.getTodoDate())
				.transportType(dto.getTransportType())
				.build();
	}

	public void updateTransportTodo(TodoModifyRequest dto) {
		this.title = dto.getTitle();
		this.contents = dto.getContents();
		this.todoDate = dto.getTodoDate();
		this.isPrivate = dto.getIsPrivate();
		this.transportType = dto.getTransportType();
		
	}
	
//	@Id
//	@GeneratedValue
//	private Long ttIdx;
//	//투두카드 3번
//	@ManyToOne
//	@JoinColumn(name = "todoType")
//	private Todo todo;
//	//교통수단 날짜
//	@Column(columnDefinition = "timestamp default now()")
//	private LocalDateTime todoDate;

//	//제목
//	private String title;
//	//내용
//	private String contents;
	
//	//카드 공개여부
//	@ColumnDefault("false")
//	private Boolean isPrivate;
}
