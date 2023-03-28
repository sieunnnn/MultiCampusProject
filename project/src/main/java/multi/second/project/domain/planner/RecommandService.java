package multi.second.project.domain.planner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import multi.second.project.domain.group.domain.TravelGroup;
import multi.second.project.domain.group.repository.TravelGroupRepository;
import multi.second.project.domain.member.UserPrincipal;
import multi.second.project.domain.planner.dto.ChatRequest;
import multi.second.project.domain.planner.dto.ChatResponse;
import multi.second.project.domain.planner.dto.PlanRecommandRequest;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommandService {

	private final TravelGroupRepository travelGroupRepository;
	
//	public String generateTravelPlan(PlanRecommandRequest dto) {
//		
//		String question = dto.getMonth() + "월에 " + dto.getParticipantCnt() + "명이 함께 가는" + dto.getLocation() + " 여행 일정 짜줘";
//		return openAI.chatCompletion(question);
//	}
	
	public ChatResponse generateChat(ChatRequest dto) {
		
		ChatResponse res = new ChatResponse();
		res.setMessage(dto.getMessage());
		res.setUserId(dto.getUserId());
		return res;
	}

	public Long findTravelGroupIdxByUserId() {
		//기존 코드 변경으로 임시 수정
		//TravelGroup group = travelGroupRepository.findTravelGroupByMembersUserId(UserPrincipal.getUserPrincipal().getUserId());
		return 4L;
	}
	
	
}
