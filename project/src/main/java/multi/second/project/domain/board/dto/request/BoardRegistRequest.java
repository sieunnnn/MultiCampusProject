package multi.second.project.domain.board.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.board.domain.BoardCategory;

@Data
@NoArgsConstructor
public class BoardRegistRequest {

	private String userId;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;
	
	private String boardCategory;
	
}
