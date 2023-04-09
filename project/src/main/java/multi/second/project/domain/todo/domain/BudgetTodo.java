package multi.second.project.domain.todo.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import multi.second.project.domain.todo.dto.request.BudgetTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.BudgetTodoRegistRequest;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@SuperBuilder @NoArgsConstructor @AllArgsConstructor @Getter
public class BudgetTodo extends Todo{

	@Enumerated(EnumType.STRING)
	private BudgetType budgetType;
	
	//예산비
	private Integer budget;
	
	public static BudgetTodo createBudgetTodo(BudgetTodoRegistRequest dto) {
		return BudgetTodo.builder()
				.contents(dto.getContents())
				.title(dto.getTitle())
				.todoDate(dto.getTodoDate())
				.isPrivate(dto.getIsPrivate())
				.todoType(TodoType.Budget)
				.budget(dto.getBudget())
				.budgetType(dto.getBudgetType())
				.build();
	}

	public void updateBudgetTodo(BudgetTodoModifyRequest dto) {
		this.title = dto.getTitle();
		this.contents = dto.getContents();
		this.todoDate = dto.getTodoDate();
		this.isPrivate = dto.getIsPrivate();
		this.budget = dto.getBudget();
		this.budgetType = dto.getBudgetType();
	}

//	@Id
//	@GeneratedValue
//	private Long btIdx;
//	
//	//투두카드 5번
//	@ManyToOne
//	@JoinColumn(name = "todoType")
//	private Todo todo;
	
//	//지출예정 일자
//	@Column(columnDefinition = "timestamp default now()")
//	private LocalDateTime todoDate;
	
//	//제목
//	private String title;
//	//내용
//	private String contents;
//	//예산 종류(1:식비 ~)
//	private Integer budgetType;

	//투두카드 공개여부
//	@ColumnDefault("false")
//	private Boolean isPrivate;
}
