package multi.second.project.domain.friend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
    @DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
    @DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
    @Builder @NoArgsConstructor @AllArgsConstructor @Getter

    public class Friend {
        @Id
        @GeneratedValue
        private Long frIdx;

    //	//나
    //	@ManyToOne
    //	@JoinColumn(name = "userId")
    //	private Member member;

        //친구 ID
        @OneToOne
        private Member member;

    //	//친구 ID
    //	@OneToMany
    //	private List<Member> members = new ArrayList<>();

        //친구 등록 시간
        @Column(columnDefinition = "timestamp default now()")
        private LocalDateTime regDate;

        //친구 삭제 여부
        @ColumnDefault("false")
        private Boolean isDel;

            // Getter, Setter, Constructor 생략
    }
