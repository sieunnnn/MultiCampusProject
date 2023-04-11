package multi.second.project.domain.profile.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.profile.dto.request.ProfileModifyRequest;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Profile {

	@Id
	@GeneratedValue
	private Long pfIdx;
	
	//나
	@OneToOne
	@JoinColumn(name = "userId")
	private Member member;

	//프로필 이미지 경로
	private String imagePath;

	public void updateProfile(ProfileModifyRequest dto) {
		// TODO Auto-generated method stub
		this.imagePath = dto.getImagePath();
	}

}
