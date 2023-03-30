package multi.second.project.domain.group.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.comment.domain.Comment;
import multi.second.project.domain.comment.dto.response.CommentResponse;
import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.group.domain.TravelGroup;
import multi.second.project.domain.planner.domain.Participant;
import multi.second.project.domain.planner.dto.response.ParticipantResponse;
import multi.second.project.infra.util.file.dto.FilePathDto;

@Data
@NoArgsConstructor
public class GroupResponse {
	
	private Long tgIdx;
	private List<ParticipantResponse> participantResponses = new ArrayList<ParticipantResponse>();
	
	public GroupResponse(TravelGroup travelGroup) {
		this.tgIdx = travelGroup.getTgIdx();
		this.participantResponses = ParticipantResponse.toDtoList(travelGroup.getParticipants());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
