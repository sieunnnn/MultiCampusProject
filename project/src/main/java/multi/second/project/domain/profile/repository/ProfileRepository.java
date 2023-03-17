package multi.second.project.domain.profile.repository;


import multi.second.project.domain.profile.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository  extends JpaRepository<Profile, String> {

    // ?1은 메서드의 매개변수의 순서 위치다.
    @Query("select t from Profile t where t.member.userId = ?1")
    List<Profile> findProfileByMemberUserId(String userId);
}
