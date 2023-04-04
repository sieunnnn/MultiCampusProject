package multi.second.project.domain.note.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.note.domain.Partner;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
public class PartnerResponse {
    private Member member;
    private Long pnIdx;

    //    private String lastMassage;
    public PartnerResponse(Partner partner) {
        this.pnIdx = partner.getPnIdx();
       this.member= partner.getMember();
    }

    public static List<PartnerResponse> toDtoList(List<Partner> entityList){
        return entityList.stream().map(e -> new PartnerResponse(e)).collect(toList());
    }
}

