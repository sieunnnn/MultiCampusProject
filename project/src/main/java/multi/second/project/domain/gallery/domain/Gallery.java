package multi.second.project.domain.gallery.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multi.second.project.domain.comment.domain.Comment;
import multi.second.project.domain.comment.dto.request.CommentRegistRequest;
import multi.second.project.domain.gallery.dto.request.GalleryModifyRequest;
import multi.second.project.domain.gallery.dto.request.GalleryRegistRequest;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.infra.util.file.FilePath;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Gallery {
	
	@Id
	@GeneratedValue
	private Long postIdx;
	
	//댓글 작성자//(피드백)학생 과 교실을 생각하라. 교실이 학생정보를 갖고 있는것
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<Comment> comments = new ArrayList<>();
	
	//포스트 작성자
	@ManyToOne
	@JoinColumn(name = "userId")
	private Member member;
	
	//포스트 작성 시간
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	//포스트 제목
	private String title;
	
	//포스트 글
	private String content;
	
	//포스트 삭제 여부
	@ColumnDefault("false")
	private Boolean isDel;
	
	//포스트 이미지 파일
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<FilePath> files = new ArrayList<FilePath>();

	public static Gallery createGallery(GalleryRegistRequest dto, Member member) {
		return Gallery.builder()
				.member(member)
				.title(dto.getTitle())
				.content(dto.getContent())
				.build();
	}  //테이블 행에 요청 받은 정보를 채우는 메서드 - 서비스에서 활용
	public void addFile(FilePath filePath) {
		this.files.add(filePath);
	}

	public void removeFile(FilePath filePath) {
		this.files.remove(filePath);
	}

	public void updateGallery(GalleryModifyRequest dto) {
		this.title = dto.getTitle();
		this.content = dto.getContent();
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	
	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}

//	public void setFiles(List<FilePath> files) { this.files.addAll(files);
//	}


	// createGallery(), addFile(), removeFile(), updateGallery(), addComment(), removeComment() 메서드가 정의되어 있습니다.
	// 이는 게시글에 대한 CRUD(Create, Read, Update, Delete) 기능 및 댓글 추가, 삭제 기능을 구현하기 위한 메서드들입니다.
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
