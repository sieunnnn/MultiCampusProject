package multi.second.project.domain.planner.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.group.domain.TravelGroup;
import multi.second.project.domain.host.domain.Host;

@Data
@NoArgsConstructor
public class PlannerGroupSearchRequest {
;
	private String keyword;
	
}
