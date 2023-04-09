package multi.second.project.domain.board.repository;

import multi.second.project.domain.board.domain.Board;

import java.util.List;

    public interface BoardRepositoryExtension {

        List<Board> testQueryDSL(String title, boolean isDel);


}
