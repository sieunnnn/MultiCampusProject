package multi.second.project.domain.todo.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.host.domain.Host;
import multi.second.project.domain.todo.domain.BudgetType;
import multi.second.project.domain.todo.domain.TransportType;

@Data
@NoArgsConstructor
public class TodoDeleteRequest {

	private Long tdIdx;
	

}
