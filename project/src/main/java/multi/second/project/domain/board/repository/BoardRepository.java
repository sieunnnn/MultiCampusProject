package multi.second.project.domain.board.repository;


import multi.second.project.domain.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryExtension{
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}

