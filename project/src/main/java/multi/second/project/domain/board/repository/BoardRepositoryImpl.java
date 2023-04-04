package multi.second.project.domain.board.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import multi.second.project.domain.board.domain.Board;
import multi.second.project.domain.board.domain.QBoard;

import java.util.List;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryExtension{

	private final JPAQueryFactory query;
	private QBoard board = QBoard.board;
	
	@Override
	public List<Board> testQueryDSL(String title, boolean isDel) {
		
		List<Board> boards = query.select(board)
				.from(board)
				.where(board.title.contains(title).and(board.isDel.eq(isDel)))
				.fetch();
		
		return boards;
	}

}
