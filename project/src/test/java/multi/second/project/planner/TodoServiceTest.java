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
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.member.dto.request.SignUpRequest;
import multi.second.project.domain.member.repository.MemberRepository;
import multi.second.project.domain.planner.PlannerService;
import multi.second.project.domain.planner.domain.Participant;
import multi.second.project.domain.planner.domain.Planner;
import multi.second.project.domain.planner.dto.request.PlannerGroupModifyRequest;
import multi.second.project.domain.planner.repository.ParticipantRepository;
import multi.second.project.domain.planner.repository.PlannerRepository;
import multi.second.project.domain.todo.domain.AccomodationTodo;
import multi.second.project.domain.todo.domain.AttractionsTodo;
import multi.second.project.domain.todo.domain.BudgetTodo;
import multi.second.project.domain.todo.domain.BudgetType;
import multi.second.project.domain.todo.domain.GeneralTodo;
import multi.second.project.domain.todo.domain.Todo;
import multi.second.project.domain.todo.domain.TodoType;
import multi.second.project.domain.todo.domain.TransportTodo;
import multi.second.project.domain.todo.domain.TransportType;
import multi.second.project.domain.todo.repository.AccomodationRepository;
import multi.second.project.domain.todolist.domain.TodoList;
import multi.second.project.domain.todolist.repository.TodoListRepository;
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
	private TodoListRepository todoListRepository;
	
	@Autowired
	MockMvc mockMvc;
	

	@Test//플랜 생성 테스트(사전에 유저가 있어야됨)//planner.java 의 todolist에 fetch = FetchType.EAGER옵션이 있어야됨
	public void testCreateFull() {
		
		//숙박 todo DB 저장
		Todo accomodationTodo = AccomodationTodo.builder().address("더미데이터 숙박 todo 주소 3")
								.contents("더미데이터 숙박 todo 내용 3")
								.title("더미데이터 숙박 todo 제목 3")
								.todoType(TodoType.Accomodation)
								.build();
		
//		accomadationRepository.save(entity);
		
		//todolist DB 저장
		TodoList todoList = TodoList.builder().title("더미데이터 todolist 제목 3")
								.build();
	
		//todolist에 위의 숙박todo 넣기
		todoList.addTodo(accomodationTodo);
		
		todoListRepository.save(todoList);
		
		//자신의 로그인 아이디 불러오기(아이디 수동 입력,해당아이디가 Member DB에 있어야됨)
		Member group1A = memberRepository.findById("group1A").get();
		//host는 회원가입시에 hostDB도 만들어지도록 하고 플래너 만들때는 userId로 호스트를 찾아 플래너에 매핑하려 한다.
//		Host host = hostRepository.findByMemberUserId("group1A");
		
		//여행 그룹은 플래너 만들때 그룹을 생성시켜서 그룹인덱스도 만들어지고 그룹에 기본으로 플래너 만든 자신을 넣으려고 한다.
		//근데 유저가 기존 그룹에 포함되어있으면 다른그룹에 속할 수 없는것 같다? -> manytomany로 교체
		
		//플래너에 들어갈 호스트 정보 넣기(기본-자신)
		Participant hostParticipant =  Participant.builder().member(group1A).build();
		participantRepository.save(hostParticipant);
		Host host = Host.builder().participant(hostParticipant).build();
		hostRepository.save(host);
		
		//플래너에 들어갈 그룹 정보 넣기(기본-자신)
		Participant groupParticipant =  Participant.builder().member(group1A).build();
		participantRepository.save(groupParticipant);
		TravelGroup group = new TravelGroup();
		group.addParticipant(groupParticipant);
		travelGroupRepository.save(group);
		
		//호스트와 그룹정보를 넣은 플래너 만들기
		Planner planner = Planner.builder()
								.title("더미데이터 플래너 제목 3")
								.host(host)
								.travelGroup(group)
								.build();
		
		//플래너에 위의 todolist 넣기
		planner.addTodoList(todoList);
		
		plannerRepository.save(planner);
		
	}
	
	//todolist 추가
	@Test
	public void testAddTodolist() throws Exception {
		
		//todolist 추가 할 플래너 찾기(인덱스 수동 입력)
		Planner planner = plannerRepository.findById(5L).get();
		
		//todolist DB 저장
		TodoList todoList = TodoList.builder()
									.title("더미데이터 todolist 테스트 1")
									.build();
		
		//플래너에 위의 todolist 넣기
		planner.addTodoList(todoList);
		plannerRepository.save(planner);
	}
	
	//todolist 삭제
		@Test
		public void testDelTodolist() throws Exception {
			
			//todolist 추가 할 플래너 찾기(인덱스 수동 입력)
			Planner planner = plannerRepository.findById(5L).get();
			
			//todolist DB 삭제
			TodoList todoList = todoListRepository.findById(28L).get();
			
			//플래너에 위의 todolist 넣기
			planner.removeTodoList(todoList);
			plannerRepository.save(planner);
		}
	
	
	//todo추가
	//todolist 자바에서 추가하고자 하는 종류의 todo 매핑에 fetch = FetchType.EAGER를 추가하고 다른곳은 삭제해야함)
	@Test
	public void testAddTodo() throws Exception {
		
		//todolist 찾기
		TodoList todoList = todoListRepository.findById(18L).get();
		
		//숙박 todo DB 저장
//		AccomodationTodo accomodationTodo = AccomodationTodo.builder().address("더미데이터 숙박 todo 주소 1")
//				.contents("더미데이터 숙박 todo 내용 1")
//				.title("더미데이터 숙박 todo 제목 1")
//				.build();
		//관광지 todo DB 저장
		AttractionsTodo attractionsTodo = AttractionsTodo.builder().attractions("더미데이터 관광지 todo 주소 1")
				.contents("더미데이터 관광지 todo 내용 1")
				.title("더미데이터 관광지 todo 제목 1")
				.build();
//		//예산 todo DB 저장
//		BudgetTodo budgetTodo = BudgetTodo.builder().budget(10000)
//				.budgetType(BudgetType.FOOD)
//				.contents("더미데이터 예산 todo 내용 1")
//				.title("더미데이터 예산 todo 제목 1")
//				.build();
//		//일반 todo DB 저장
//		GeneralTodo generalTodo = GeneralTodo.builder().address("더미데이터 일반 todo 주소 1")
//				.contents("더미데이터 일반 todo 내용 1")
//				.title("더미데이터 일반 todo 제목 1")
//				.build();
//		//교통 todo DB 저장
//		TransportTodo transportTodo = TransportTodo.builder().transportType(TransportType.AIRPLANE)
//				.contents("더미데이터 교통 todo 내용 1")
//				.title("더미데이터 교통 todo 제목 1")
//				.build();

		
//		//todolist에 위의 숙박todo 넣기
//		todoList.accomodationAddTodo(accomodationTodo);
		//todolist에 위의 관광지todo 넣기
		todoList.attractionsAddTodo(attractionsTodo);
//		//todolist에 위의 예산todo 넣기
//		todoList.budgetAddTodo(budgetTodo);
//		//todolist에 위의 일반todo 넣기
//		todoList.generalAddTodo(generalTodo);
//		//todolist에 위의 교통todo 넣기
//		todoList.transportAddTodo(transportTodo);
		
		todoListRepository.save(todoList);
		
	}
	
	
	
	
	//플래너 제거 혹은 공유인원 제거
	//테스트하려면 planner.java 에 있는 fetch = FetchType.EAGER 을 제거하고, TravelGroup의 participants에 fetch = FetchType.EAGER 옵션을 넣어야함
	@Test
	public void testDeletePlnner() {
		
		//플래너를 제거하려는 인원
		String userId = "group1B";
		
		//제거하려는 플래너
		Planner planner = plannerRepository.findById(5L).get();
		System.out.println("planner.getHost().getParticipant().getMember().getUserId() : " + planner.getHost().getParticipant().getMember().getUserId());
		
		//플래너를 제거하려는 인원이 호스트가 아니면 그룹에서 그 인원을 제거
		if(!planner.getHost().getParticipant().getMember().getUserId().equals(userId)) {
			System.out.println("host != member");
			Member member = memberRepository.findById(userId).get();
			TravelGroup travelGroup = travelGroupRepository.findById(planner.getTravelGroup().getTgIdx()).get();
			System.out.println("travelGroup.getParticipants() : " + travelGroup.getParticipants());
			List<Participant> participants = travelGroup.getParticipants();
			for (Participant participant : participants) {
				System.out.println("participant.getMember().getUserId() : " + participant.getMember().getUserId());
				if(participant.getMember().getUserId().equals(userId)) {
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
	//플래너 그룹에 인원 추가
	@Test
	public void testAddGroupMember2() {
		
		PlannerGroupModifyRequest dto = new PlannerGroupModifyRequest();
				
		dto.setNewUserId("group1D");
		
		plannerService.addPlannerGroup(dto, 5L);
	}
	
	@Test
	public void testAddGroupMember() {
		
//		dto.setNewUserId("group1B");
//		dto.setTpIdx(7L);
//		
//		plannerService.addPlannerGroup(dto);
//		//이거 안되네
		
		//추가할 멤버
		Member group1B = memberRepository.findById("group1B").get();
		Participant participant =  Participant.builder().member(group1B).build();
		participantRepository.save(participant);
		
		//멤버를 추가할 플래너
		Planner planner = plannerRepository.findById(5L).get();

		//그룹에 멤버 추가
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
	
	//todorepository 시험
//		@Test
//		public void testTodoRepository() throws Exception {
//			
//			
//			//todolist DB 저장
//			List<TodoList> todoLists = todoListRepository.findByTodosTdIdx(7L);
//			
//			for (TodoList todoList : todoLists) {
//				
//				System.out.println(todoList.getTitle());
//				
//				
//			}
//		}
//	
	
	
	
	
	
	
	

}
