package multi.second.project.domain.todolist.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.planner.domain.Planner;
import multi.second.project.domain.todo.domain.AccomodationTodo;
import multi.second.project.domain.todo.domain.Todo;
import multi.second.project.domain.todolist.domain.TodoList;


@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long>, TodoListRepositoryExtension{

	TodoList findByTodosTdIdx(Long tdIdx);
	
	TodoList findTodoListByTodosTdIdx(Long tdIdx);
	
}
