package multi.second.project.gallery;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import multi.second.project.domain.member.dto.Principal;

@SpringBootTest
@AutoConfigureMockMvc
public class GalleryControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testUpload() throws Exception {
		
		Principal principal = new Principal();
		principal.setUserId("group1A");
		
		MockMultipartFile file1 = new MockMultipartFile("files", "ofn1.txt", "text/plain", "파일업로드테스트1".getBytes());
		MockMultipartFile file2 = new MockMultipartFile("files", "ofn2.txt", "text/plain", "파일업로드테스트2".getBytes());
		
		mockMvc.perform(multipart("/gallery/upload")
				.file(file1)
				.file(file2)
				.param("title", "게시글업로드테스트3")
				.param("content", "게시글업로드테스트3 중입니다.")
				.sessionAttr("auth", principal))
		.andDo(print());
	}
}
