package multi.second.project.domain.note.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import multi.second.project.domain.comment.domain.Comment;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.message.domain.Message;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Note {

	@Id
	@GeneratedValue
	private Long ntIdx;
	
	//나
//	@ManyToOne
//	@JoinColumn(name = "userId")
//	private Member member;
	
	//상대방
//	@OneToOne
//	@JoinColumn(name = "userId")
//	private Member member2;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<Member> members = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<Message> messages = new ArrayList<>();
	
//	//상대방
//	@OneToOne
//	private Member partner;
	
	//쪽지 처음 만들어진 시간
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	//쪽지 삭제 여부
	@ColumnDefault("false")
	private Boolean isDel;
}
