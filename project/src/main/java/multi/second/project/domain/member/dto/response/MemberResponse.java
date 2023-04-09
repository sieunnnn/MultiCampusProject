package multi.second.project.domain.member.dto.response;


import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.note.domain.Note;
import multi.second.project.infra.util.file.dto.FilePathDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MemberResponse {

    private String userId;
    private String email;
    private String grade;

    public MemberResponse(Member member) {
        this.userId = member.getUserId();
        this.email = member.getEmail();
        this.grade = member.getGrade();
    }


}
