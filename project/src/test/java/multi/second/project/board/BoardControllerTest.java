package multi.second.project.board;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import multi.second.project.domain.member.dto.Principal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testUpload() throws Exception {

        Principal principal = new Principal();
        principal.setUserId("test");

        MockMultipartFile file1 = new MockMultipartFile("files", "ofn1.txt", "text/plain", "파일업로드테스트1".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("files", "ofn2.txt", "text/plain", "파일업로드테스트2".getBytes());

        mockMvc.perform(multipart("/board/upload")
                        .file(file1)
                        .file(file2)
                        .param("title", "게시글업로드테스트")
                        .param("content", "게시글업로드테스트 중입니다.")
                        .sessionAttr("auth", principal))
                .andDo(print());
    }
}
