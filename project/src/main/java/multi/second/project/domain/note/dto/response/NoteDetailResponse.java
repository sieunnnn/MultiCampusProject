package multi.second.project.domain.note.dto.response;

import lombok.Getter;
import lombok.Setter;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.message.domain.Message;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class NoteDetailResponse {
    private Long ntIdx;
    private List<Member> members;
    private List<Message> messages;
    private LocalDateTime regDate;
    private Boolean isDel;
}
