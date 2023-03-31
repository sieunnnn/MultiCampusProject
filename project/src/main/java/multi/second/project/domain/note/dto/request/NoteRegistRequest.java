package multi.second.project.domain.note.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.message.domain.Message;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class NoteRegistRequest {

    private Long ntIdx;

    private List<Member> members = new ArrayList<>();

    private List<Message> messages = new ArrayList<>();
}
