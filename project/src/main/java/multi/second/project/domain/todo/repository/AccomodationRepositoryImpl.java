package multi.second.project.domain.todo.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class AccomodationRepositoryImpl implements AccomodationRepositoryExtension{

	private final JPAQueryFactory query;
	
	public AccomodationRepositoryImpl(JPAQueryFactory query) {
		this.query = query;
	}
	


}
