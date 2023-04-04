package multi.second.project.domain.board.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class BoardRegistRequest {

	private String userId;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;
	
}
