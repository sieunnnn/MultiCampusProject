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

//이 코드는 갤러리 게시물의 리스트를 응답하기 위한 DTO 클래스입니다.
//
//Gallery 엔티티를 받아와서 GalleryListResponse 클래스로 매핑해주는 역할을 합니다.
// 생성자에서는 Gallery 엔티티에서 게시물의 고유번호, 제목, 작성자 아이디, 썸네일 이미지 등을 받아옵니다.
//
//그리고 toDtoList 메서드에서는 Gallery 엔티티의 리스트를 GalleryListResponse DTO의 리스트로 변환합니다.
// Stream의 map 메서드를 이용해서 Gallery 엔티티를 GalleryListResponse DTO로 변환한 후,
// collect 메서드를 이용해서 리스트로 만듭니다.