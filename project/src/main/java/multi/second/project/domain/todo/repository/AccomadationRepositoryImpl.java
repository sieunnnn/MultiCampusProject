package multi.second.project.domain.todo.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.gallery.domain.QGallery;

public class AccomadationRepositoryImpl implements AccomadationRepositoryExtension{

	private final JPAQueryFactory query;
	
	public AccomadationRepositoryImpl(JPAQueryFactory query) {
		this.query = query;
	}
	


}
