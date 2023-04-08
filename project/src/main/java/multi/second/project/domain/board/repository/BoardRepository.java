package multi.second.project.domain.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import multi.second.project.domain.board.domain.Board;
import multi.second.project.domain.board.domain.BoardCategory;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryExtension{
	
	Page<Board> findByBoardCategory(BoardCategory boardCategory, Pageable pageable);

}
