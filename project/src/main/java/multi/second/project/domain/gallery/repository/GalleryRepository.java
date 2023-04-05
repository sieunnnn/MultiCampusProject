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

//위 코드는 Spring Data JPA에서 제공하는 JpaRepository를 상속받은 인터페이스인 GalleryRepository입니다.
// JpaRepository는 기본적인 CRUD(Create, Read, Update, Delete) 기능을 지원하는 인터페이스로,
// 개발자가 구현하지 않아도 자동으로 구현됩니다.
//
//GalleryRepository에서는 JpaRepository를 상속받아 제공되는 메서드 외에 findByMemberUserId() 메서드가 정의되어 있습니다.
// 이 메서드는 member의 userId를 기준으로 Gallery를 조회하기 위해 정의한 메서드입니다.
// 이를 통해 특정 회원이 작성한 Gallery 목록을 조회할 수 있습니다. Pageable 객체를 인자로 받아 페이징 처리된 결과를 반환합니다.
//
//또한, GalleryRepository는 GalleryRepositoryExtension 인터페이스를 상속받습니다.
// 이는 Custom한 메서드를 추가하기 위한 인터페이스로, JpaRepository를 상속받아 구현할 수 있습니다.




//findByMemberUserId는 Spring Data JPA에서 자동으로 생성되는 쿼리 메서드 중 하나입니다.
// 이 메서드는 Gallery 엔티티에서 member 필드의 userId 값을 기준으로 페이징된 결과를 조회합니다.
//예를 들어, findByMemberUserId("john", PageRequest.of(0, 10))를 호출하면
// member 필드의 userId 값이 "john"인 Gallery 엔티티들 중에서 첫 번째 페이지의 10개의 결과를 조회하게 됩니다.
//Pageable 객체는 두 개의 인자를 받는데, 첫 번째는 조회하고자 하는 페이지 번호를 의미하고,
// 두 번째는 한 페이지당 조회할 결과의 개수를 의미합니다.
// PageRequest.of(0, 10)는 첫 번째 페이지에서 10개의 결과를 조회하라는 것을 의미합니다.




//Pageable은 Spring Data JPA에서 제공하는 페이징 기능을 사용할 때 사용하는 인터페이스입니다.
// 페이지 번호와 페이지 당 표시할 항목 수 등을 정의하고, JPA 쿼리에 적용할 수 있습니다.
//JPA에서는 보통 검색 결과를 List나 Array 형태로 반환하는데, 이 방식은 결과 집합의 크기가 커지면 메모리를 많이 소비하고,
// 대량의 데이터를 처리할 때 성능이 떨어질 수 있습니다.
// 따라서, Spring Data JPA에서는 검색 결과를 페이지 단위로 나누어 반환하는 페이징 기능을 제공하고 있습니다.
// Pageable을 이용하여 페이지 번호와 페이지 크기 등을 지정하면, JPA 쿼리가 실행될 때 해당 정보를 기반으로 페이징 처리를 수행하고,
// 결과를 Page 객체에 담아 반환합니다. 이를 이용하면 대량의 데이터를 처리할 때 효율적인 검색이 가능합니다.