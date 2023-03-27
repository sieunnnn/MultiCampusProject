package multi.second.project.infra.util.file;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multi.second.project.infra.util.file.dto.FilePathDto;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
@EqualsAndHashCode
public class FilePath {
	
	@Id
	@GeneratedValue
	private Long fpIdx;
	
	private String originFileName;
	private String renameFileName;
	private String savePath;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	@ColumnDefault("false")
	private Boolean isDel;
	
	private String groupName;

	public static FilePath createFilePath(FilePathDto dto) {
		return FilePath.builder()
				.originFileName(dto.getOriginFileName())
				.renameFileName(dto.getRenameFileName())
				.savePath(dto.getSavePath())
				.groupName(dto.getGroupName())
				.build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
