package multi.second.project.member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import multi.second.project.domain.member.dto.request.SignUpRequest;
import multi.second.project.domain.member.dto.response.MemberResponse;
import multi.second.project.domain.member.repository.MemberRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testSendAuthenticateMail() throws Exception {
		mockMvc.perform(post("/member/signup")
				.param("userId", "test")
				.param("password", "1234")
				.param("email", "azimemory@gmail.com"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("회원가입 정보 저장")
	public void testSignUpImpl() throws Exception {
		
		SignUpRequest form = new SignUpRequest();
		form.setUserId("group1E");
		form.setPassword("123qwe!@#QWE");
		form.setEmail("azimemory@gmail.com");
		form.setGrade("ROLE_USER");
		//form.setImagePath(null);
		
		mockMvc.perform(get("/member/signupimpl/1234")
				.sessionAttr("signupForm", form)
				.sessionAttr("authToken", "1234"))
		.andExpect(status().is3xxRedirection());
	}
	
	
	@Test
	@DisplayName("멤버 동적쿼리")
	public void dynamicQueryWithBookOr() {

		// 사용자화면에서 사용자가 검색조건을 체크하고 키워드를 입력할 수 있다.
		List<String> filters = List.of("userId");
		String keyword = "A";
		
		memberRepository.dynamicQueryWithMemberOr(filters, keyword).forEach(e -> {
			System.out.println(new MemberResponse(e));
		});
	}
	
	
	
	
	
	
	
	
	
	

}
