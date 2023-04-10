package multi.second.project.domain.note.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.*;

import multi.second.project.domain.note.dto.request.NoteRegistRequest;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Note {

	@Id
	@GeneratedValue
	private Long ntIdx;

	//나
	@ManyToOne
	@JoinColumn(name = "userId")
	private Member member;
	//게스트??
	private String guest;

	//상대방
//	@OneToOne
//	@JoinColumn(name = "userId")
//	private Member member2;

//	@OneToMany(fetch = FetchType.EAGER)
//	private List<Partner> partners = new ArrayList<>();

//	@OneToMany(cascade = CascadeType.ALL)
//	@Builder.Default
//	private List<Message> messages = new ArrayList<>();

	//상대방
//	@OneToOne
//	private Member partner;

	//쪽지 처음 만들어진 시간
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;

	//쪽지 삭제 여부
	@ColumnDefault("false")
	private Boolean isDel;

		public static Note createNote(NoteRegistRequest dto, Member member) {

			return Note.builder()
					.member(member)
					.guest(dto.getGuest())
					.build();
	}

//	public void addPartner(Partner partner) {
//		partners.add(partner);
//
//	}

}