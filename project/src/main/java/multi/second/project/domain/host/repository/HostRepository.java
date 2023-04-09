package multi.second.project.domain.host.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import multi.second.project.domain.group.domain.TravelGroup;
import multi.second.project.domain.host.domain.Host;
import multi.second.project.domain.member.domain.Member;

@Repository
public interface HostRepository extends JpaRepository<Host, Long>{
	//Host findByMemberUserId(String UserId);
}
