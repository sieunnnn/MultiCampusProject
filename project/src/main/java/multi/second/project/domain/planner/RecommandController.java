package multi.second.project.domain.planner;

import java.util.Map;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import multi.second.project.domain.member.UserPrincipal;
import multi.second.project.domain.planner.dto.ChatRequest;

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
	  @MessageMapping("/chat-plan/{tpIdx}/{tgIdx}")
	  public void chat(@DestinationVariable("tpIdx") Long tpIdx, @DestinationVariable("tgIdx") Long tgIdx, ChatRequest dto) throws Exception {
		  simpMessagingTemplate.convertAndSend("/topic/planner-message/" + tpIdx,
					Map.of("type","chat-plan","msg", recommandService.generateChat(dto)));
	  }
	
//	  @MessageMapping("/recommand-plan/{tgIdx}")
//	  public void recommandPlan(@DestinationVariable("tgIdx") Long tgIdx, PlanRecommandRequest dto) throws Exception {
//	      simpMessagingTemplate.convertAndSend("/topic/recommand-plan/" + tgIdx, recommandService.generateTravelPlan(dto));
//	  }
//	  @MessageMapping("/recommand-plan/{tgIdx}")
//	  public void recommandPlan(@DestinationVariable("tgIdx") Long tgIdx, ChatRequest dto) throws Exception {
//		  simpMessagingTemplate.convertAndSend("/topic/recommand-plan/" + tgIdx, recommandService.generateChat(dto));
//	  }
	  @MessageMapping("/recommand-plan/{tgIdx}")
	  public void recommandPlan(@DestinationVariable("tgIdx") Long tgIdx, ChatRequest dto) throws Exception {
		  simpMessagingTemplate.convertAndSend("/topic/recommand-plan/" + tgIdx, recommandService.generateChat(dto));
	  }
	  @MessageMapping("/recommand-plan2/{tgIdx}")
	  public void recommandPlan2(@DestinationVariable("tgIdx") Long tgIdx, ChatRequest dto) throws Exception {
		  simpMessagingTemplate.convertAndSend("/topic/recommand-plan2/" + tgIdx, recommandService.generateChat(dto));
	  }
//	  @MessageMapping("/recommand-plan/{tgIdx}")
//	  public void recommandPlan(@DestinationVariable("tgIdx") Long tgIdx, @RequestParam Map<String, String> message) throws Exception {
//		  return Map.of("message" ,"["+ UserPrincipal.getUserPrincipal().getUserId() +"]"+" : " );
//	  }
	
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
}
