package multi.second.project.domain.todolist.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
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
import multi.second.project.domain.planner.domain.Planner;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class TodoList {

	@Id
	@GeneratedValue
	private Long tlIdx;
	
	@ManyToOne
	@JoinColumn(name = "tpIdx")
	private Planner planner;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	private String title;
	
	@ColumnDefault("false")
	private Boolean isDel;
}
