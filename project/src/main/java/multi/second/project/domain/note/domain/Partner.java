//package multi.second.project.domain.note.domain;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import multi.second.project.domain.member.domain.Member;
//import multi.second.project.domain.message.domain.Message;
//import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
//@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
//@Builder @NoArgsConstructor @AllArgsConstructor @Getter
//public class Partner {
//
//	@Id
//	@GeneratedValue
//	private Long pnIdx;
//
//	@ManyToOne
//	//@JoinColumn(name = "userId")
//	private Member member;
//
////	@OneToMany(cascade = CascadeType.ALL)
////	@Builder.Default
////	private List<Message> messages = new ArrayList<>();
//
//	@ManyToOne
//	@JoinColumn(name= "ntIdx")
//	private Note note;
//
//	@Builder.Default
//	private LocalDateTime createdAt=LocalDateTime.now();
//
//
//	private LocalDateTime deletedAt;
//
//
//}
