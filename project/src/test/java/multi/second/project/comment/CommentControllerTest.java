package multi.second.project.comment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import multi.second.project.domain.comment.dto.request.CommentRegistRequest;
import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.group.repository.TravelGroupRepository;
import multi.second.project.domain.member.dto.Principal;
import multi.second.project.domain.member.repository.MemberRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private TravelGroupRepository travelGroupRepository;
	
	@Test
	@DisplayName("댓글 정보 저장")
	public void testUpload() throws Exception {
		
		Principal principal = new Principal();
		principal.setUserId("group1B");
		
//		Gallery gallery = new Gallery();
//		gallery.s

		mockMvc.perform(post("/comment/upload")
				.param("context", "안녕하세요. 5댓글입니다.")
				.sessionAttr("auth", principal))
		.andDo(print());
	}
	
	@Test
	public void testCreateComment() {
		
//		Member group1A = memberRepository.findById("group1A").get();
//		Member group1B = memberRepository.findById("group1B").get();
//		Member group2A = memberRepository.findById("group2A").get();
//		Member group2B = memberRepository.findById("group2B").get();
//		
//		TravelGroup group1 = new TravelGroup();
//		TravelGroup group2 = new TravelGroup();
//		
//		group1.addMembers(group1A);
//		group1.addMembers(group1B);
//		
//		group2.addMembers(group2A);
//		group2.addMembers(group2B);
//		
//		travelGroupRepository.save(group1);
//		travelGroupRepository.save(group2);
		
//		Comment a = commetRepository.findById().get();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
