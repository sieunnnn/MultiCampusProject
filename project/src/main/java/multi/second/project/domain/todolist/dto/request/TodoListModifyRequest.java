package multi.second.project.domain.todolist.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.host.domain.Host;

@Data
@NoArgsConstructor
public class TodoListModifyRequest {

	private Long tlIdx;
	private String title;
	
}
