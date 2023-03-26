package multi.second.project.planner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import multi.second.project.domain.host.domain.Host;
import multi.second.project.domain.member.dto.request.SignUpRequest;
import multi.second.project.domain.planner.domain.Planner;
import multi.second.project.domain.planner.repository.PlannerRepository;
import multi.second.project.domain.todo.domain.AccomodationTodo;
import multi.second.project.domain.todo.domain.Todo;
import multi.second.project.domain.todo.domain.TransportTodo;
import multi.second.project.domain.todo.repository.AccomadationRepository;
import multi.second.project.domain.todolist.domain.TodoList;


@SpringBootTest
@AutoConfigureMockMvc
public class TodoServiceTest {
	
	@Autowired
	private AccomadationRepository accomadationRepository;
	
	@Autowired
	private PlannerRepository plannerRepository;

	@Test
	public void test() {
		
		AccomodationTodo accomodationTodo = AccomodationTodo.builder().address("더미데이터 부산")
								.contents("더미데이터 내용")
								.title("더미데이터 제목")
								.build();
		
		
//		accomadationRepository.save(entity);
		TodoList todoList = TodoList.builder().title("더미데이터 3월 23일")
								.build();
	
		todoList.addTodo(accomodationTodo);
		
		
		Planner planner = Planner.builder()
								.title("더미데이터 플래너 제목").build();
		
		planner.addTodoList(todoList);
		
		plannerRepository.save(planner);
		
	}
	
	@Test
	public void testFind() {
		Planner planner = plannerRepository.findById(1L).get();
		
		List<TodoList> todolists = planner.getTodolists();
		
		
		for (TodoList todoList : todolists) {
		
			System.out.println(todoList);
			List<Todo> todos = todoList.getTodos();
			for (Todo todo : todos) {
				System.out.println(todo.getTitle());
				System.out.println(todo instanceof AccomodationTodo);
				AccomodationTodo accomodationTodo = (AccomodationTodo) todo;
				System.out.println(accomodationTodo.getAddress());
				
				
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	

}
