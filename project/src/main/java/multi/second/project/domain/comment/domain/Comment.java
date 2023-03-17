package multi.second.project.domain.comment.domain;

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
import multi.second.project.domain.comment.dto.request.CommentModifyRequest;
import multi.second.project.domain.comment.dto.request.CommentRegistRequest;
import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.member.domain.Member;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Comment {
	@Id
	@GeneratedValue
	private Long cmIdx;
	
	//댓글 단 시간
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	//(피드백)양방향 매핑은 위험하니 되도록이면 안하는게 좋다.postIdx 조인한거 뺌
	
	//댓글 작성자
	@ManyToOne
	//@JoinColumn(name = "userId")
	private Member member;
	
	//댓글 내용
	private String context;
	
	//댓글 삭제여부
	@ColumnDefault("false")
	private Boolean isDel;

//	public static Comment addComment(CommentRegistRequest dto, Gallery gallery) {
//		return Comment.builder()
//				.gallery(gallery)
//				.commenterId(dto.getCommenterId())
//				.context(dto.getContext())
//				.build();
//	}
	public static Comment createComment(CommentRegistRequest dto, Member member) {
	return Comment.builder()
			.member(member)
			.context(dto.getContext())
			.build();
}

	public void updateComment(CommentModifyRequest dto) {
		this.context = dto.getContext();
	}
}
