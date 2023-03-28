package multi.second.project.domain.host.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
import multi.second.project.domain.host.dto.request.HostRegistRequest;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.planner.domain.Participant;
import multi.second.project.domain.planner.domain.Planner;
import multi.second.project.domain.planner.dto.request.PlannerRegistRequest;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Host {//(피드백)프랜드처럼
//일단 보류..
	@Id
	@GeneratedValue
	private Long hostIdx;
	
//	@ManyToOne
//	private Member member;
	@OneToOne
	private Participant participant = new Participant();
	
	// 시간
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;

//	//친구 삭제 여부
//	@ColumnDefault("false")
//	private Boolean isDel;
	public static Host createHost(Participant participant) {
		return Host.builder()
				.participant(participant)
				.build();
		
	}
}
