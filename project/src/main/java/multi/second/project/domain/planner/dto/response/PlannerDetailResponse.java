package multi.second.project.domain.planner.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.comment.dto.response.CommentResponse;
import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.group.domain.TravelGroup;
import multi.second.project.domain.host.domain.Host;
import multi.second.project.domain.planner.domain.Planner;
import multi.second.project.domain.todolist.dto.response.TodoListResponse;
import multi.second.project.infra.util.file.dto.FilePathDto;

@Data
@NoArgsConstructor
public class PlannerDetailResponse {
	
	private Long tpIdx;
	private Long tgIdx;
	private String title;
	private LocalDateTime regDate;
//	private String userId;
	private TravelGroup travelGroup;
	private Host host;
	private Boolean isPrivate;
	private List<TodoListResponse> todolistResponses = new ArrayList<TodoListResponse>();
	
	public PlannerDetailResponse(Planner planner) {
		this.tpIdx = planner.getTpIdx();
		this.tgIdx = planner.getTravelGroup().getTgIdx();
		this.title = planner.getTitle();
		this.regDate = planner.getRegDate();
//		this.userId = planner.getMember().getUserId();
		this.travelGroup = planner.getTravelGroup();
		this.host = planner.getHost();
		this.todolistResponses = TodoListResponse.toDtoList(planner.getTodolists());
	}
	
	public String getRegDateAsDate() {
		return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getRegDateAsTime() {
		return regDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
