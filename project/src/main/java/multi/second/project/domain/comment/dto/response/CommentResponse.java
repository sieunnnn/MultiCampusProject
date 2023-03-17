package multi.second.project.domain.comment.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.comment.domain.Comment;

@Data
@NoArgsConstructor
public class CommentResponse {

	private Long cmIdx;
	private Long postIdx;
	private String userId;
	private String context;
	private LocalDateTime regDate;

	public CommentResponse(Comment entity) {
		this.cmIdx = entity.getCmIdx();
		this.context = entity.getContext();
		this.regDate = entity.getRegDate();
		this.userId = entity.getMember().getUserId();
	}
	
	public String getRegDateAsDate() {
		return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getRegDateAsTime() {
		return regDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	public static List<CommentResponse> toDtoList(List<Comment> entityList){
		return entityList.stream().map(e -> new CommentResponse(e)).collect(Collectors.toList());
	}

}
