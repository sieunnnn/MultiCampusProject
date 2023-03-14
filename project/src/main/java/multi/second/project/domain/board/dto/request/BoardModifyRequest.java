package multi.second.project.domain.board.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardModifyRequest {

	private Long bdIdx;
	private String userId;
	private String title;
	private String content;
	private List<Long> delFiles = new ArrayList<Long>();
	
}
