package multi.second.project.domain.planner.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multi.second.project.domain.group.domain.TravelGroup;
import multi.second.project.domain.host.domain.Host;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.todolist.domain.TodoList;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Planner {

	@Id
	@GeneratedValue
	private Long tpIdx;
	
	//나
//	@ManyToOne
//	@JoinColumn(name = "userId")
//	private Member member;
	
	//플래너를 공유한 그룹
	@OneToOne
	@JoinColumn(name = "tgIdx")
	private TravelGroup travelGroup;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<TodoList> todolists = new ArrayList<>();
	
	//플래너 생성 날짜
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	//플래너 제목
	private String title;
	
	//플래너 호스트
	@OneToOne
	private Host host;
	
	//플래너 공개여부
	@ColumnDefault("false")
	private Boolean isPrivate;
	
	//플래너 삭제여부
	@ColumnDefault("false")
	private Boolean isDel;
}
