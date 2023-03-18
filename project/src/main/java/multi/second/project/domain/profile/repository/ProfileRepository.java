package multi.second.project.domain.profile.repository;


import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.profile.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProfileRepository  extends JpaRepository<Profile, String> {

    // ?1은 메서드의 매개변수의 순서 위치다.
    @Query("select t from Profile t where t.member.userId = :userId")
    Profile findProfileByMemberUserId(String userId);

    @Modifying
    @Transactional
    @Query("update Profile t set t.member = :member, t.imagePath = :imagePath where t.member.userId = :userId")
    void updateById(Member member, String imagePath, String userId);
}
