package multi.second.project.domain.profile.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.profile.domain.Profile;
import multi.second.project.infra.code.Code;

@Data
@NoArgsConstructor
public class ProfileModifyRequest {

	private Long pfIdx;

	private String userId;

	private String imagePath;

	public ProfileModifyRequest(Profile entity) {
		this.pfIdx = entity.getPfIdx();
		this.imagePath = entity.getImagePath();
		this.userId = entity.getMember().getUserId();
	}

	public String getFullPath() {
		return Code.PROFILE_STORAGE_PATH + "/" + imagePath;
	}
}
