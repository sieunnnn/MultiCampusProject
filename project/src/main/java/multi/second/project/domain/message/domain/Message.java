package multi.second.project.domain.message.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.note.domain.Note;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Message {

	@Id
	@GeneratedValue
	private Long msIdx;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "ntIdx")
	private Note note;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	private String content;
	
	@ColumnDefault("false")
	private Boolean isDel;
	
}
