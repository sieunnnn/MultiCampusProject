package multi.second.project.domain.todo.dto.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.host.domain.Host;
import multi.second.project.domain.todo.domain.BudgetType;
import multi.second.project.domain.todo.domain.TransportType;

@Data
@NoArgsConstructor
public class AccomodationTodoModifyRequest {

	private Long tdIdx;
	private String title;
	private String contents;
	private LocalDateTime todoDate;
	private Boolean isPrivate;
	private String address;
	
}
