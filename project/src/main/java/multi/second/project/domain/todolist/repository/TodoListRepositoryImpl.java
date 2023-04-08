package multi.second.project.domain.todolist.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.gallery.domain.QGallery;

public class TodoListRepositoryImpl implements TodoListRepositoryExtension{

	private final JPAQueryFactory query;
	
	public TodoListRepositoryImpl(JPAQueryFactory query) {
		this.query = query;
	}
	


}
