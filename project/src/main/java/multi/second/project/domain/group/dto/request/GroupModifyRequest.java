package multi.second.project.domain.group.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;

@Data
@NoArgsConstructor
public class GroupModifyRequest {

	private Long tgIdx;
	
	private List<Member> members = new ArrayList<>();
	
}
