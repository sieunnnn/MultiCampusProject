package multi.second.project.domain.comment.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.gallery.domain.QGallery;

public class CommentRepositoryImpl implements CommentRepositoryExtension{

	private final JPAQueryFactory query;
	
	public CommentRepositoryImpl(JPAQueryFactory query) {
		this.query = query;
	}
	
}
