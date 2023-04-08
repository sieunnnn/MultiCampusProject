package multi.second.project.domain.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import multi.second.project.domain.group.domain.TravelGroup;

@Repository
public interface TravelGroupRepository extends JpaRepository<TravelGroup, Long>{

//	TravelGroup findTravelGroupByMembersUserId(String userId);
	
//	TravelGroup findByParticipantsPcIdx(Long pcIdx);
}
