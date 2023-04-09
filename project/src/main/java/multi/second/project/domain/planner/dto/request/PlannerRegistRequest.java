package multi.second.project.domain.planner.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.host.domain.Host;

@Data
@NoArgsConstructor
public class PlannerRegistRequest {

	private String userId;
	
//	private Long tgIdx;
	
	@NotEmpty
	private String title;
	
//	@NotEmpty
//	private Host host;
	private Boolean isPrivate;
}
