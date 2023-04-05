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

//갤러리 엔티티는 주로 비즈니스 로직을 담당합니다. 예를 들어, 갤러리 엔티티에는 갤러리를 생성하는 로직, 갤러리를 수정하는 로직, 갤러리를 삭제하는 로직 등이 포함됩니다.
// 이러한 비즈니스 로직은 주로 엔티티에서 처리됩니다.
//
//하지만 엔티티를 직접 데이터 전송 객체로 사용하면 위험합니다. 예를 들어, 갤러리 엔티티에는 삭제 여부를 나타내는 isDel 필드가 있습니다.
// 이 필드는 삭제된 갤러리를 조회할 때 사용됩니다. 하지만 이 필드를 데이터 전송 객체로 사용하면 삭제 여부가 노출될 수 있습니다.
// 따라서 엔티티를 데이터 전송 객체로 사용하는 것은 비즈니스 로직과 데이터 전송을 혼동시킬 수 있으며, 보안상의 문제가 발생할 수 있습니다.
// 이러한 이유로, 엔티티와 데이터 전송 객체는 구분하여 사용하는 것이 좋습니다.