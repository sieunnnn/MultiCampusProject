package multi.second.project.domain.planner.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.comment.domain.Comment;
import multi.second.project.domain.comment.dto.response.CommentResponse;
import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.group.domain.TravelGroup;
import multi.second.project.domain.host.domain.Host;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.planner.domain.Participant;
import multi.second.project.domain.planner.domain.Planner;
import multi.second.project.domain.todolist.dto.response.TodoListResponse;
import multi.second.project.infra.util.file.dto.FilePathDto;

@Data
@NoArgsConstructor
public class ParticipantResponse {
	
	private Long pcIdx;
	private Member member;
	
	public ParticipantResponse(Participant participant) {
		this.pcIdx = participant.getPcIdx();
		this.member = participant.getMember();
		
	}
	
	public static List<ParticipantResponse> toDtoList(List<Participant> entityList){
		return entityList.stream().map(e -> new ParticipantResponse(e)).collect(Collectors.toList());
	}
	
	

}
