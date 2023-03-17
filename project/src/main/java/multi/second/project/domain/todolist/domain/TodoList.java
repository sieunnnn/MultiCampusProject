package multi.second.project.domain.todolist.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import multi.second.project.domain.todo.domain.Todo;

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
}
