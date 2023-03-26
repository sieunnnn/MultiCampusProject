package multi.second.project.member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import multi.second.project.domain.member.dto.request.SignUpRequest;


@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
	
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
		form.setUserId("group2B");
		form.setPassword("123qwe!@#QWE");
		form.setEmail("azimemory@gmail.com");
		form.setGrade("ROLE_USER");
		//form.setImagePath(null);
		
		mockMvc.perform(get("/member/signupimpl/1234")
				.sessionAttr("signupForm", form)
				.sessionAttr("authToken", "1234"))
		.andExpect(status().is3xxRedirection());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
