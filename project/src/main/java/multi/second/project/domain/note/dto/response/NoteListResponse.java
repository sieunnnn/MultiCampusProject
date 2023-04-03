package multi.second.project.domain.note.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.member.dto.response.MemberListResponse;
import multi.second.project.domain.note.domain.Note;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class NoteListResponse {

    private Long ntIdx;
    private List<MemberListResponse> memberListResponse = new ArrayList<>();
//    private String lastMassage;
//    private LocalDateTime regDate;

    public NoteListResponse(Note note) {
        this.ntIdx = note.getNtIdx();
        this.memberListResponse =  MemberListResponse.toDtoList(note.getMembers());
    }

}

