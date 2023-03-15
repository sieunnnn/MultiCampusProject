package multi.second.project.domain.todo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multi.second.project.domain.todolist.domain.TodoList;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@NoArgsConstructor @AllArgsConstructor @Getter @Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Todo {
	//투두 카드 번호
	@Id
	@GeneratedValue
	private Long tdIdx;
	
	//투두카드 생성 일자
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	//투두실행 일자
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime todoDate;

	//투두카드 제목
	private String title;
	
	//투두카드 내용
	private String contents;
	
	//투두카드 공개여부
	@ColumnDefault("false")
	private Boolean isPrivate;
	
	//투두카드 삭제 여부
	@ColumnDefault("false")
	private Boolean isDel;
	
//	//투두리스트 번호
//	@ManyToOne
//	@JoinColumn(name = "tlIdx")
//	private TodoList todoList;
	
//	//투두카드 타입(1:일반 투두카드,2:관광지 투두카드,3:교통 투두카드,4:숙박 투두카드,5:예산 투두카드)
//	private Integer todoType;

}
