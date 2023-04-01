package multi.second.project.domain.gallery.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import multi.second.project.domain.comment.domain.Comment;
import multi.second.project.domain.gallery.domain.Gallery;


@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long>, GalleryRepositoryExtension{

	Page<Gallery> findByMemberUserId(String userId, Pageable pageable);
//	List<Gallery> findCommentByPostIdx(Long postIdx);
	
}

//findByMemberUserId는 Spring Data JPA에서 자동으로 생성되는 쿼리 메서드 중 하나입니다.
// 이 메서드는 Gallery 엔티티에서 member 필드의 userId 값을 기준으로 페이징된 결과를 조회합니다.
//
//예를 들어, findByMemberUserId("john", PageRequest.of(0, 10))를 호출하면
// member 필드의 userId 값이 "john"인 Gallery 엔티티들 중에서 첫 번째 페이지의 10개의 결과를 조회하게 됩니다.
//
//Pageable 객체는 두 개의 인자를 받는데, 첫 번째는 조회하고자 하는 페이지 번호를 의미하고,
// 두 번째는 한 페이지당 조회할 결과의 개수를 의미합니다.
// PageRequest.of(0, 10)는 첫 번째 페이지에서 10개의 결과를 조회하라는 것을 의미합니다.