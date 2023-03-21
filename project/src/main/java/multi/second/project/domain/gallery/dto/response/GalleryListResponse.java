package multi.second.project.domain.gallery.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.gallery.domain.Gallery;

import static java.util.stream.Collectors.*;

@Data
@NoArgsConstructor
public class GalleryListResponse {

	private Long postIdx;
	private String userId;
	private String title;
	private LocalDateTime regDate;

	private Long thumb;


	public GalleryListResponse(Gallery entity) {
		this.postIdx = entity.getPostIdx();
		this.title = entity.getTitle();
		this.userId = entity.getMember().getUserId();
		this.thumb = entity.getFiles() == null || entity.getFiles().size() == 0
				? 13L // 썸네일이 없을때 대표이미지
				: entity.getFiles().get(0).getFpIdx(); // 처음 이미지 ID
	}

	public static List<GalleryListResponse> toDtoList(List<Gallery> entityList){
		return entityList.stream().map(e -> new GalleryListResponse(e)).collect(toList());
	}

}
