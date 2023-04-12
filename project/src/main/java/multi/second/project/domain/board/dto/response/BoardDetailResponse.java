package multi.second.project.domain.board.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.board.domain.Board;
import multi.second.project.domain.board.domain.BoardCategory;
import multi.second.project.infra.util.file.dto.FilePathDto;

@Data
@NoArgsConstructor
public class BoardDetailResponse {
	
	private Long bdIdx;
	private String title;
	private LocalDateTime regDate;
	private String userId;
	private String content;
	private BoardCategory boardCategory;
	private List<FilePathDto> filePathDtos = new ArrayList<FilePathDto>();
	
	public BoardDetailResponse(Board board) {
		this.bdIdx = board.getBdIdx();
		this.title = board.getTitle();
		this.regDate = board.getRegDate();
		this.userId = board.getMember().getUserId();
		this.content = board.getContent();
		this.boardCategory = board.getBoardCategory();
		this.filePathDtos = FilePathDto.toDtoList(board.getFiles());
	}
	
	public String getRegDateAsDate() {
		return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getRegDateAsTime() {
		return regDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
