package multi.second.project.domain.member.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

	@NotBlank(message = "아이디를 입력해주세요.")
	private String userId;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String password;
	
}
