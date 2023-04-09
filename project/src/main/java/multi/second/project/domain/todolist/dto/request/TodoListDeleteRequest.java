package multi.second.project.domain.todolist.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.host.domain.Host;

@Data
@NoArgsConstructor
public class TodoListDeleteRequest {

	private Long tlIdx;
	
}
