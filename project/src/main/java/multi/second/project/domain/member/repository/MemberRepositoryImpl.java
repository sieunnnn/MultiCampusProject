package multi.second.project.domain.member.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.member.domain.QMember;

public class MemberRepositoryImpl implements MemberRepositoryExtension{

    private final JPAQueryFactory queryFactory;
    private QMember member = QMember.member;

    public MemberRepositoryImpl(EntityManager em) {
    	this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
	public List<Member> dynamicQueryWithMemberOr(List<String> filters, String keyword) {
		
		List<Member> members = 
				queryFactory.select(member)
				.from(member)
				.where(keywordContains(filters, keyword))
				.fetch();
		
		return members;
	}

    private BooleanExpression keywordContains(List<String> filters, String keyword) {
		
		BooleanExpression predicate = null;
		
		if(filters.contains("userId"))
			predicate = member.userId.contains(keyword);
		
		
		return predicate;
	}
   
}