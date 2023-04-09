package multi.second.project.domain.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfileDTO {

    private Long pfIdx;
    private Member member;
    private String imagePath;
}
