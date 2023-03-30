package multi.second.project.domain.member.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multi.second.project.domain.friend.domain.Friend;
import multi.second.project.domain.member.dto.request.SignUpRequest;
import multi.second.project.domain.note.domain.Note;
import multi.second.project.domain.planner.domain.Planner;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Member {
	
	@Id
	private String userId;
	private String password;
	private String email;
	private String grade;
	//private String imagePath;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@Builder.Default
//	private List<Friend> friends = new ArrayList<>();
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@Builder.Default
//	private List<Planner> planners = new ArrayList<>();
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@Builder.Default
//	private List<Note> notes = new ArrayList<>();
	
	@ColumnDefault("false")
	private Boolean isLeave;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;

	public static Member createMember(SignUpRequest dto) {
		return Member.builder()
				.userId(dto.getUserId())
				.password(dto.getPassword())
				.email(dto.getEmail())
				.grade(dto.getGrade())
				//.imagePath(dto.getImagePath())
				.build();
	}
	

	
	
	
	
	
	
	
	
	
	
	
}
