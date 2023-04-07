package multi.second.project.domain.member.repository;

import java.util.List;
import multi.second.project.domain.member.domain.Member;


public interface MemberRepositoryExtension {

	List<Member> dynamicQueryWithMemberOr(List<String> filters, String keyword);

}