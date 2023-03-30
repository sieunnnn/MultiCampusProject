package multi.second.project.domain.friend.domain;

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
import multi.second.project.domain.board.domain.Board;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.infra.util.file.FilePath;
//
//@Entity
//@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
//@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
//@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Friend {

//
//	@Id
//	@GeneratedValue
//	private Long frIdx;
//	
////	//나
////	@ManyToOne
////	@JoinColumn(name = "userId")
////	private Member member;
//	
////	//친구 ID
////	@OneToOne
////	private Member member;
//	
////	//친구 ID
////	@OneToMany
////	private List<Member> members = new ArrayList<>();
//	
//	//친구 등록 시간
//	@Column(columnDefinition = "timestamp default now()")
//	private LocalDateTime regDate;
//
//	//친구 삭제 여부
//	@ColumnDefault("false")
//	private Boolean isDel;
}
