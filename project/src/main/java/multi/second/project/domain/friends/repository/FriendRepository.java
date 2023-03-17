package multi.second.project.domain.friends.repository;

import multi.second.project.domain.friends.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    // 추가적인 메소드가 필요하다면 여기에 선언할 수 있습니다.
}
