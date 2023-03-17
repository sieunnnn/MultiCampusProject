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
	
	//List<Gallery> findCommentByPostIdx(Long postIdx);
	
}
