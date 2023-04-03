package multi.second.project.domain.note.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class NoteListResponse {

    private Long ntIdx;
    private List<String> userId;
    private String lastMassage;
    private LocalDateTime regDate;
}
