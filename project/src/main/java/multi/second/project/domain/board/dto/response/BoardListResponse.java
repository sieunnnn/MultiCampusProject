package multi.second.project.domain.board.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.board.domain.Board;
import multi.second.project.domain.board.domain.BoardCategory;

@Data
@NoArgsConstructor
public class BoardListResponse {

	private Long bdIdx;
	private String userId;
	private String title;
	private BoardCategory boardCategory;
	private LocalDateTime regDate;

	public BoardListResponse(Board entity) {
		this.bdIdx = entity.getBdIdx();
		this.title = entity.getTitle();
		this.regDate = entity.getRegDate();
		this.userId = entity.getMember().getUserId();
		this.boardCategory = entity.getBoardCategory();
	}
	
	public String getRegDateAsDate() {
		return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getRegDateAsTime() {
		return regDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	public static List<BoardListResponse> toDtoList(List<Board> entityList){
		return entityList.stream().map(e -> new BoardListResponse(e)).collect(Collectors.toList());
	}

	
	
	
	
	
	
	
	
	
	
}
