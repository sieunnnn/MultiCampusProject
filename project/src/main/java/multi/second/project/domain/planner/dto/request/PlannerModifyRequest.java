package multi.second.project.domain.planner.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.host.domain.Host;

@Data
@NoArgsConstructor
public class PlannerModifyRequest {

	private Long tpIdx;
	private String userId;
	private String title;
	private Host host;
	
}
