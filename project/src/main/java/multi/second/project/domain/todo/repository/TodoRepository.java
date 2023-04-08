package multi.second.project.domain.todo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.planner.domain.Planner;
import multi.second.project.domain.todo.domain.AccomodationTodo;
import multi.second.project.domain.todo.domain.Todo;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{

	
	
}
