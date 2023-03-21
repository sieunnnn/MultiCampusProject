package multi.second.project.domain.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;

@Data
@NoArgsConstructor
public class Principal {
	
	private String userId;
	private String password;
	private String email;
	private String grade;
	//private String imagePath;

	public Principal(Member member) {
		this.userId = member.getUserId();
		this.password = member.getPassword();
		this.email = member.getEmail();
		this.grade = member.getGrade();
		//this.imagePath = member.getImagePath();
	}

	
	
}
