package multi.second.project.domain.todo;

import java.util.Map;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import multi.second.project.domain.todo.dto.request.AccomodationTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.AccomodationTodoRegistRequest;
import multi.second.project.domain.todo.dto.request.AttractionsTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.AttractionsTodoRegistRequest;
import multi.second.project.domain.todo.dto.request.BudgetTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.BudgetTodoRegistRequest;
import multi.second.project.domain.todo.dto.request.GeneralTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.GeneralTodoRegistRequest;
import multi.second.project.domain.todo.dto.request.TodoDeleteRequest;
import multi.second.project.domain.todo.dto.request.TransportTodoModifyRequest;
import multi.second.project.domain.todo.dto.request.TransportTodoRegistRequest;

@Controller
@AllArgsConstructor
@RequestMapping("todo")
public class TodoController {
	
	private final TodoService todoService;
	private final SimpMessagingTemplate simpMessagingTemplate;

	//생성은 따로따로 만드나
	//그럼 수정은? 삭제는?
	
	@MessageMapping("upload-accomodation/{tpIdx}/{tlIdx}")
	public void uploadAccomodation(
			@DestinationVariable("tpIdx") Long tpIdx,
			@DestinationVariable("tlIdx") Long tlIdx,
			AccomodationTodoRegistRequest dto
			) throws Exception {
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","upload-accomodation","msg",todoService.createAccomodationTodo(dto, tpIdx, tlIdx),"tlIdx",tlIdx));
		
	}
	
	@MessageMapping("upload-attractions/{tpIdx}/{tlIdx}")
	public void uploadAttractions(
			@DestinationVariable("tpIdx") Long tpIdx,
			@DestinationVariable("tlIdx") Long tlIdx,
			AttractionsTodoRegistRequest dto
			) throws Exception {
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","upload-attractions","msg",todoService.createAttractionsTodo(dto, tpIdx, tlIdx),"tlIdx",tlIdx));
		
	}
	
	@MessageMapping("upload-budget/{tpIdx}/{tlIdx}")
	public void uploadBudget(
			@DestinationVariable("tpIdx") Long tpIdx,
			@DestinationVariable("tlIdx") Long tlIdx,
			BudgetTodoRegistRequest dto
			) throws Exception {
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","upload-budget","msg",todoService.createBudgetTodo(dto, tpIdx, tlIdx),"tlIdx",tlIdx));
		
	}
	
	@MessageMapping("upload-general/{tpIdx}/{tlIdx}")
	public void uploadGeneral(
			@DestinationVariable("tpIdx") Long tpIdx,
			@DestinationVariable("tlIdx") Long tlIdx,
			GeneralTodoRegistRequest dto
			) throws Exception {
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","upload-general","msg",todoService.createGeneralTodo(dto, tpIdx, tlIdx),"tlIdx",tlIdx));
		
	}
	
	@MessageMapping("upload-transport/{tpIdx}/{tlIdx}")
	public void uploadTransport(
			@DestinationVariable("tpIdx") Long tpIdx,
			@DestinationVariable("tlIdx") Long tlIdx,
			TransportTodoRegistRequest dto
			) throws Exception {
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","upload-transport","msg",todoService.createTransportTodo(dto, tpIdx, tlIdx),"tlIdx",tlIdx));
		
	}
	
	//////////////////////////
	
	
	
	@MessageMapping("modify-accomodation/{tpIdx}")
	public void modifyAccomodation(
			@DestinationVariable("tpIdx") Long tpIdx,
			AccomodationTodoModifyRequest dto
			) throws Exception {
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","modify-accomodation","msg",todoService.modifyAccomodationTodo(dto)));
		
	}
	@MessageMapping("modify-attractions/{tpIdx}")
	public void modifyAttractions(
			@DestinationVariable("tpIdx") Long tpIdx,
			AttractionsTodoModifyRequest dto
			) throws Exception {
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","modify-attractions","msg",todoService.modifyAttractionsTodo(dto)));
		
	}
	@MessageMapping("modify-budget/{tpIdx}")
	public void modifyBudget(
			@DestinationVariable("tpIdx") Long tpIdx,
			BudgetTodoModifyRequest dto
			) throws Exception {
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","modify-budget","msg",todoService.modifyBudgetTodo(dto)));
		
	}
	@MessageMapping("modify-general/{tpIdx}")
	public void modifyGeneral(
			@DestinationVariable("tpIdx") Long tpIdx,
			GeneralTodoModifyRequest dto
			) throws Exception {
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","modify-general","msg",todoService.modifyGeneralTodo(dto)));
		
	}
	@MessageMapping("modify-transport/{tpIdx}")
	public void modifyTransport(
			@DestinationVariable("tpIdx") Long tpIdx,
			TransportTodoModifyRequest dto
			) throws Exception {
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","modify-transport","msg",todoService.modifyTransportTodo(dto)));
		
	}
	
	///////////////////////
	
	@MessageMapping("remove-accomodation/{tpIdx}")
	public void removeAccomodation(
			@DestinationVariable("tpIdx") Long tpIdx,
			TodoDeleteRequest dto
			) {
		todoService.AccomodationDeleteTodo(dto);
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","remove-accomodation","msg",dto.getTdIdx()));
		
	}
	@MessageMapping("remove-attractions/{tpIdx}")
	public void removeAttractions(
			@DestinationVariable("tpIdx") Long tpIdx,
			TodoDeleteRequest dto
			) {
		todoService.AttractionsDeleteTodo(dto);
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","remove-attractions","msg",dto.getTdIdx()));
	}
	@MessageMapping("remove-budget/{tpIdx}")
	public void removeBudget(
			@DestinationVariable("tpIdx") Long tpIdx,
			TodoDeleteRequest dto
			) {
		todoService.BudgetDeleteTodo(dto);
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","remove-budget","msg",dto.getTdIdx()));
	}
	@MessageMapping("remove-general/{tpIdx}")
	public void removeGeneral(
			@DestinationVariable("tpIdx") Long tpIdx,
			TodoDeleteRequest dto
			) {
		todoService.GeneralDeleteTodo(dto);
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","remove-general","msg",dto.getTdIdx()));
	}
	@MessageMapping("remove-transport/{tpIdx}")
	public void removeTransport(
			@DestinationVariable("tpIdx") Long tpIdx,
			TodoDeleteRequest dto
			) {
		todoService.TransportDeleteTodo(dto);
		simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx, 
				Map.of("type","remove-transport","msg",dto.getTdIdx()));
	}
	
	
	
	
}
