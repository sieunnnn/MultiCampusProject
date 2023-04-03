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
public class MemberListResponse { //위치랑 이름을 바꿔야함

    private String userId;

    public MemberListResponse(Member entity){
        this.userId = entity.getUserId();
    }
    public static List<MemberListResponse> toDtoList(List<Member> entity){
        return entity.stream()
                .map(e -> new MemberListResponse(e))
                .collect(Collectors.toList());
    }


}
