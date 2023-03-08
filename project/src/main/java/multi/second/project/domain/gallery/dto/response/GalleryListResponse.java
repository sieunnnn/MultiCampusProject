package multi.second.project.domain.gallery.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.gallery.domain.Gallery;

@Data
@NoArgsConstructor
public class GalleryListResponse {

	private Long postIdx;
	private String userId;
	private String title;
	private LocalDateTime regDate;

	public GalleryListResponse(Gallery entity) {
		this.postIdx = entity.getPostIdx();
		this.title = entity.getTitle();
		this.regDate = entity.getRegDate();
		this.userId = entity.getMember().getUserId();
	}
	
	public String getRegDateAsDate() {
		return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getRegDateAsTime() {
		return regDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	public static List<GalleryListResponse> toDtoList(List<Gallery> entityList){
		return entityList.stream().map(e -> new GalleryListResponse(e)).collect(Collectors.toList());
	}

	
	
	
	
	
	
	
	
	
	
}
