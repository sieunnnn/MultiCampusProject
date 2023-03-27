package multi.second.project.domain.gallery.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.comment.domain.Comment;
import multi.second.project.domain.comment.dto.response.CommentResponse;
import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.infra.util.file.dto.FilePathDto;

@Data
@NoArgsConstructor
public class GalleryDetailResponse {
	
	private Long postIdx;
	private String title;
	private LocalDateTime regDate;
	private String userId;
	private String content;
	private List<FilePathDto> filePathDtos = new ArrayList<FilePathDto>();
	private List<CommentResponse> commentResponses = new ArrayList<CommentResponse>();
	
	public GalleryDetailResponse(Gallery gallery) {
		this.postIdx = gallery.getPostIdx();
		this.title = gallery.getTitle();
		this.regDate = gallery.getRegDate();
		this.userId = gallery.getMember().getUserId();
		this.content = gallery.getContent();
		this.filePathDtos = FilePathDto.toDtoList(gallery.getFiles());
		this.commentResponses = CommentResponse.toDtoList(gallery.getComments());
	}
	
	public String getRegDateAsDate() {
		return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getRegDateAsTime() {
		return regDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
