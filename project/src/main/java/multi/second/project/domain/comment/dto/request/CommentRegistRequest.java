package multi.second.project.domain.comment.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRegistRequest {

	//private Long postIdx;
	
	private String userId;
	
	@NotEmpty
	private String context;
	
}
