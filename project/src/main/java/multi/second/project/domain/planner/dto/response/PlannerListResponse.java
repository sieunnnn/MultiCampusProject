package multi.second.project.domain.planner.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.group.domain.TravelGroup;
import multi.second.project.domain.host.domain.Host;
import multi.second.project.domain.planner.domain.Planner;

@Data
@NoArgsConstructor
public class PlannerListResponse {

	private Long tpIdx;
//	private String userId;
	private String title;
	private LocalDateTime regDate;
	private Host host;

	private TravelGroup travelGroup;


	public PlannerListResponse(Planner entity) {
		this.tpIdx = entity.getTpIdx();
		this.title = entity.getTitle();
		this.regDate = entity.getRegDate();
		this.host = entity.getHost();
		this.travelGroup = entity.getTravelGroup();
//		this.userId = entity.getMember().getUserId();
	}
	
	public String getRegDateAsDate() {
		return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getRegDateAsTime() {
		return regDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	public static List<PlannerListResponse> toDtoList(List<Planner> entityList){
		return entityList.stream().map(e -> new PlannerListResponse(e)).collect(Collectors.toList());
	}

}
