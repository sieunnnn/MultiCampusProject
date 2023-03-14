package multi.second.project.domain.todo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class AttractionsTodo {

	@Id
	@GeneratedValue
	private Long atIdx;
	
	//투두카드 2번
	@ManyToOne
	@JoinColumn(name = "todoType")
	private Todo todo;
	
	//관광일자
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime todoDate;
	
	//제목
	private String title;
	//내용
	private String contents;
	//관광지 정보
	private String attractions;
	
	//투두카드 공개여부
	@ColumnDefault("false")
	private Boolean isPrivate;
}
