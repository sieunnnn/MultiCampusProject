package multi.second.project.domain.message.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import multi.second.project.domain.message.dto.request.MessageRegistRequest;
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
	
	//메시지 작성자(나 혹은 상대)
	@ManyToOne
	@JoinColumn(name = "userId")
	private Member member;
	
	//메시지들을 담고있는 쪽지 번호
	@ManyToOne
	@JoinColumn(name = "ntIdx")
	private Note note;
	
	//메시지 작성 시간
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	//메시지 내용
	private String content;
	
	//메시지 내용 삭제여부
	@ColumnDefault("false")
	private Boolean isDel;

	public static Message createMessage(MessageRegistRequest dto, Member member) {
		return Message.builder()
				.member(member)
				.content(dto.getContent())
				.build();
	}

//	public void addFile(FilePath filePath) {
//		this.files.add(filePath);
//	}
//
//	public void removeFile(FilePath filePath) {
//		this.files.remove(filePath);
//	}
//
//	public void updateGallery(GalleryModifyRequest dto) {
//		this.title = dto.getTitle();
//		this.content = dto.getContent();
//	}

//	public void addComment(Comment comment) {
//		this.comments.add(comment);
//	}
//
//	public void removeComment(Comment comment) {
//		this.comments.remove(comment);
//	}

}
