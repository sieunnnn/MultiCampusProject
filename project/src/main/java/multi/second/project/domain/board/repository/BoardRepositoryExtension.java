package multi.second.project.domain.board.repository;

import java.util.List;

import multi.second.project.domain.board.domain.Board;

public interface BoardRepositoryExtension {
	
	List<Board> testQueryDSL(String title, boolean isDel);

}
