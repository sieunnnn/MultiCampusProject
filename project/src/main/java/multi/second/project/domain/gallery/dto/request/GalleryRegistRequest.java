package multi.second.project.domain.gallery.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GalleryRegistRequest {

	// 갤러리에 사진을 등록할때 화면에 필요한 데이터 정보들

	private String userId;
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;

}
