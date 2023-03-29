package multi.second.project.planner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.FetchType;
import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import multi.second.project.domain.group.domain.TravelGroup;
import multi.second.project.domain.group.repository.TravelGroupRepository;
import multi.second.project.domain.host.domain.Host;
import multi.second.project.domain.host.repository.HostRepository;
import multi.second.project.domain.member.MemberRepository;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.member.dto.request.SignUpRequest;
import multi.second.project.domain.planner.PlannerService;
import multi.second.project.domain.planner.domain.Participant;
import multi.second.project.domain.planner.domain.Planner;
import multi.second.project.domain.planner.dto.request.PlannerGroupModifyRequest;
import multi.second.project.domain.planner.repository.ParticipantRepository;
import multi.second.project.domain.planner.repository.PlannerRepository;
import multi.second.project.domain.todo.domain.AccomodationTodo;
import multi.second.project.domain.todo.domain.Todo;
import multi.second.project.domain.todo.domain.TransportTodo;
import multi.second.project.domain.todo.repository.AccomodationRepository;
import multi.second.project.domain.todolist.domain.TodoList;
import multi.second.project.infra.code.ErrorCode;
import multi.second.project.infra.exception.HandlableException;


@SpringBootTest
@AutoConfigureMockMvc
public class TodoServiceTest {
	
	@Autowired
	private AccomodationRepository accomadationRepository;
	
	@Autowired
	private PlannerRepository plannerRepository;
	
	@Autowired
	private HostRepository hostRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private TravelGroupRepository travelGroupRepository;
	
	@Autowired
	private ParticipantRepository participantRepository;
	
	@Autowired
	private PlannerService plannerService;
	
	@Autowired
	MockMvc mockMvc;
	

	@Test
	public void testCreateFull() {
		
		AccomodationTodo accomodationTodo = AccomodationTodo.builder().address("더미데이터 숙박todo 주소 인천숙소")
								.contents("더미데이터 숙박todo 내용 인천 어쩌구")
								.title("더미데이터 숙박todo 제목 인천숙소는여기")
								.build();
		
		
//		accomadationRepository.save(entity);
		TodoList todoList = TodoList.builder().title("더미데이터 인천 3월 27일")
								.build();
	
		todoList.accomodationAddTodo(accomodationTodo);
		
		Member group1A = memberRepository.findById("group1A").get();
		//host는 회원가입시에 hostDB도 만들어지도록 하고 플래너 만들때는 userId로 호스트를 찾아 플래너에 매핑하려 한다.
//		Host host = hostRepository.findByMemberUserId("group1A");
		
		//여행 그룹은 플래너 만들때 그룹을 생성시켜서 그룹인덱스도 만들어지고 그룹에 기본으로 플래너 만든 자신을 넣으려고 한다.
		//근데 유저가 기존 그룹에 포함되어있으면 다른그룹에 속할 수 없는것 같다? -> manytomany로 교체
		
		Participant hostParticipant =  Participant.builder().member(group1A).build();
		participantRepository.save(hostParticipant);
		Host host = Host.builder().participant(hostParticipant).build();
		hostRepository.save(host);
		
		Participant groupParticipant =  Participant.builder().member(group1A).build();
		participantRepository.save(groupParticipant);
		TravelGroup group = new TravelGroup();
		group.addParticipant(groupParticipant);
		travelGroupRepository.save(group);
		
		Planner planner = Planner.builder()
								.title("더미데이터 플래너 제목 인천여행계획")
								.host(host)
								.travelGroup(group)
								.build();
		
		planner.addTodoList(todoList);
		
		plannerRepository.save(planner);
		
	}
	
	
	
	//왜 remove가 안되지...
	@Test
	public void testDeletePlnner() {
		
		Planner planner = plannerRepository.findById(5L).get();
		System.out.println("planner.getHost().getParticipant().getMember().getUserId() : " + planner.getHost().getParticipant().getMember().getUserId());
		
		if(!planner.getHost().getParticipant().getMember().getUserId().equals("group1A")) {
			System.out.println("host != member");
			Member member = memberRepository.findById("group1A").get();
			TravelGroup travelGroup = travelGroupRepository.findById(planner.getTravelGroup().getTgIdx()).get();
			System.out.println("travelGroup.getParticipants() : " + travelGroup.getParticipants());
			List<Participant> participants = travelGroup.getParticipants();
			for (Participant participant : participants) {
				System.out.println("participant.getMember().getUserId() : " + participant.getMember().getUserId());
				if(participant.getMember().getUserId().equals("group1A")) {
					System.out.println("제거전 - " + participant.getMember().getUserId());
					travelGroup.removeParticipant(participant);
					System.out.println("제거후 - " + participant.getMember().getUserId());
					
//					participantRepository.save(participant);
					
					break;
				}
			}
			
			travelGroupRepository.save(travelGroup);
			//travelGroup.getParticipants();
			
//			<List>Participant participant = participantRepository.findAllById(travelGroup.getParticipants()).get();
//			planner.getTravelGroup().removeParticipant("group1B");
			
//			
//			travelGroup.removeParticipant(participant);
//			
//			travelGroupRepository.flush();
		}
		else {//삭제한 사람이 host일 경우 방을 삭제한다.
			System.out.println("host = member");
			plannerRepository.delete(planner);
			//그룹도 delete
		}
	}
	
//	@Test
//	public void testAddGroup() throws Exception {
//		mockMvc.perform(post("/planner/add-group")
//				.param("tpIdx", "7")
//				.param("newUserId", "group1B"))
//		.andDo(print());
//		//이것도 제대로안돼
//	}
	
	//fetch = FetchType.EAGER 설정하면되는데 다른데 설정된거 지워야함..
	@Test
	public void testAddGroupMember() {
		
//		dto.setNewUserId("group1B");
//		dto.setTpIdx(7L);
//		
//		plannerService.addPlannerGroup(dto);
//		//이거 안되네
		
		
		Member group1B = memberRepository.findById("group1B").get();
		
		Planner planner = plannerRepository.findById(5L).get();

		Participant participant =  Participant.builder().member(group1B).build();
		participantRepository.save(participant);
		
		TravelGroup group = travelGroupRepository.findById(planner.getTravelGroup().getTgIdx()).get();
		group.addParticipant(participant);
		travelGroupRepository.save(group);
		
		
	
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
