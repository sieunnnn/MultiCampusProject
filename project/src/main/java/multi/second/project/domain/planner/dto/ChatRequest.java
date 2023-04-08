package multi.second.project.domain.planner.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatRequest {
	
	private String userId;
	private String message;

}
