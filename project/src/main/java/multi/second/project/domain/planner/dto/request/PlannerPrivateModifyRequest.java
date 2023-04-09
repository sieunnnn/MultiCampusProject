package multi.second.project.domain.planner.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.host.domain.Host;

@Data
@NoArgsConstructor
public class PlannerPrivateModifyRequest {

	private Long tpIdx;
	private Boolean isPrivate;
	private Host host;
	
}
