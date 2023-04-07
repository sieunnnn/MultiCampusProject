package multi.second.project.domain.member.dto.response;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;

import static java.util.stream.Collectors.*;

@Data
@NoArgsConstructor
public class MemberResponse {

	private String userId;
	private String password;
	private String email;
	private String grade;


	public MemberResponse(Member entity) {
		this.userId = entity.getUserId();
		this.password = entity.getPassword();
		this.email = entity.getEmail();
		this.grade = entity.getGrade();
	}

	public static List<MemberResponse> toDtoList(List<Member> entityList){
		return entityList.stream().map(e -> new MemberResponse(e)).collect(toList());
	}

}
