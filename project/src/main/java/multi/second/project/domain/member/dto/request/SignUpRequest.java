package multi.second.project.domain.member.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpRequest {
	
	@NotBlank
	private String userId;
	
	@NotBlank
	private String password;
	
	@NotBlank
	@Email
	private String email;
	
	private String grade;
	
	//private String imagePath;
}
