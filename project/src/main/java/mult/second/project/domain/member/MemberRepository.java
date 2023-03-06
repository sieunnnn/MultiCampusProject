package mult.second.project.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mult.second.project.domain.member.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
	
	Member findByUserIdAndIsLeave(String userId, Boolean isLeave);

}
