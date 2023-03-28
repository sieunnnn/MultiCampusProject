package multi.second.project.domain.group.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.planner.domain.Participant;
import multi.second.project.domain.planner.domain.Planner;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class TravelGroup {
	
	@Id
	@GeneratedValue
	private Long tgIdx;
	
	//플래너 공유 인원
	@OneToMany
	private List<Participant> participants = new ArrayList<>();
	
	public void addParticipant(Participant participant) {
		participants.add(participant);
	}

	public void removeParticipant(Participant participant) {
		// TODO Auto-generated method stub
		participants.remove(participant);
	}
	
	
	
	
}
