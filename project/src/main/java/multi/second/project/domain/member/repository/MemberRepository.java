package multi.second.project.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import multi.second.project.domain.member.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>, MemberRepositoryExtension{

	Member findByUserIdAndIsLeave(String userId, Boolean isLeave);

//	@Query("select t from Member t where t.userId = ?1")
//	Member findByUserId(String userId);
	
	

}
