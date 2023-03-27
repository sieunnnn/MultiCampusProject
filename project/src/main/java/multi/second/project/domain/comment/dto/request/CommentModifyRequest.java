package multi.second.project.domain.comment.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentModifyRequest {

	private Long cmIdx;
	//private Long postIdx;
	private String userId;
	private String context;
	
}
