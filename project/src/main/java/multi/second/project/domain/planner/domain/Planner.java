package multi.second.project.domain.planner.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import multi.second.project.domain.group.domain.TravelGroup;
import multi.second.project.domain.member.domain.Member;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Planner {

	@Id
	@GeneratedValue
	private Long tpIdx;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Member member;
	
	@OneToOne
	@JoinColumn(name = "tgIdx")
	private TravelGroup travelGroup;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	private String title;
	
	private String host;
	
	@ColumnDefault("false")
	private Boolean isPrivate;
	
	@ColumnDefault("false")
	private Boolean isDel;
}
