package multi.second.project.domain.note.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.note.dto.request.NoteRegistRequest;
import multi.second.project.domain.note.dto.request.PartnerRegistRequest;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Partner {
    @Id
    @GeneratedValue
    private Long ntIdx;

    @ManyToOne
    private Member member;

    @Builder.Default
    private LocalDateTime createdAt=LocalDateTime.now();


    public static Note createPartner(PartnerRegistRequest dto, Member member) {

        return Note.builder()
                .member(member)
                //.guest(dto.getGuest())
                .build();
    }

}
