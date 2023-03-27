package multi.second.project.domain.planner;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import multi.second.project.domain.planner.dto.PlanRecommandRequest;

@Controller
@RequiredArgsConstructor
public class RecommandController {
	
	private final RecommandService recommandService;
	private final SimpMessagingTemplate simpMessagingTemplate;

	 @GetMapping("/recommand")
	 public String recommand(Model model) {
		 
		 Long tgIdx = recommandService.findTravelGroupIdxByUserId();
		 model.addAttribute("tgIdx", tgIdx);
		 return "recommand/recommand";
	 }
	
	  @MessageMapping("/recommand-plan/{tgIdx}")
	  public void recommandPlan(@DestinationVariable("tgIdx") Long tgIdx, PlanRecommandRequest dto) throws Exception {
	      simpMessagingTemplate.convertAndSend("/topic/recommand-plan/" + tgIdx, recommandService.generateTravelPlan(dto));
	  }
	
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
}
