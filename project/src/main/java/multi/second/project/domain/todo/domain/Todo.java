package multi.second.project.domain.todo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@SuperBuilder
@NoArgsConstructor @AllArgsConstructor @Getter @Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public class Todo {
	//투두 카드 번호
	@Id
	@GeneratedValue
	private Long tdIdx;
	
//	private String dType;
	@Enumerated(EnumType.STRING)
	private TodoType todoType;
	
	//투두카드 생성 일자
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	//투두실행 일자
	@Column(columnDefinition = "timestamp default now()")
	protected LocalDateTime todoDate;

	//투두카드 제목
	protected String title;
	
	//투두카드 내용
	protected String contents;
	
	//투두카드 공개여부
	@ColumnDefault("false")
	protected Boolean isPrivate;
	
	//투두카드 삭제 여부
	@ColumnDefault("false")
	protected Boolean isDel;

//	public static Todo createAccomodationTodo(TodoRegistRequest dto) {//리퀘스트는 하나로 해도 되나?
//		
//		return AccomodationTodo.builder()
//				.contents(dto.getContents())
//				.title(dto.getTitle())
//				.todoDate(dto.getTodoDate())
//				.address(dto.getAcAddress())
//				.build();
//	}
//
//	public static Todo createAttractionsTodo(TodoRegistRequest dto) {
//		return AttractionsTodo.builder()
//				.contents(dto.getContents())
//				.title(dto.getTitle())
//				.todoDate(dto.getTodoDate())
//				.attractions(dto.getAttractions())
//				.build();
//	}
//
//	public static Todo createBudgetTodo(TodoRegistRequest dto) {
//		return BudgetTodo.builder()
//				.contents(dto.getContents())
//				.title(dto.getTitle())
//				.todoDate(dto.getTodoDate())
//				.budget(dto.getBudget())
//				.budgetType(dto.getBudgetType())
//				.build();
//	}
//
//	public static Todo createGeneralTodo(TodoRegistRequest dto) {
//		return GeneralTodo.builder()
//				.contents(dto.getContents())
//				.title(dto.getTitle())
//				.todoDate(dto.getTodoDate())
//				.address(dto.getGnAddress())
//				.build();
//	}
//
//	public static Todo createTransportTodo(TodoRegistRequest dto) {
//		return TransportTodo.builder()
//				.contents(dto.getContents())
//				.title(dto.getTitle())
//				.todoDate(dto.getTodoDate())
//				.transportType(dto.getTransportType())
//				.build();
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//아 이거 todo 업데이트는 어케하냐...
//	public void updateAccomodationTodo(TodoModifyRequest dto) {
//		this.title = dto.getTitle();
//		this.contents = dto.getContents();
//		this.todoDate = dto.getTodoDate();
//		this.isPrivate = dto.getIsPrivate();
//		this.address = dto.getAcAddress();
//	}
//	
//	private Long tdIdx;
//	private String title;
//	private String contents;
//	private LocalDateTime todoDate;
//	private Boolean isPrivate;
//	
//	private String acAddress;
//	
//	private String attractions;
//	
//	private BudgetType budgetType;
//	private Integer budget;
//	
//	private String gnAddress;
//	
//	private String time;
//	private TransportType transportType;
	
	
//	//투두리스트 번호
//	@ManyToOne
//	@JoinColumn(name = "tlIdx")
//	private TodoList todoList;
	
//	//투두카드 타입(1:일반 투두카드,2:관광지 투두카드,3:교통 투두카드,4:숙박 투두카드,5:예산 투두카드)
//	private Integer todoType;

}
