package multi.second.project.domain.planner.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.gallery.domain.QGallery;

public class PlannerRepositoryImpl implements PlannerRepositoryExtension{

	private final JPAQueryFactory query;
	
	public PlannerRepositoryImpl(JPAQueryFactory query) {
		this.query = query;
	}
	


}
