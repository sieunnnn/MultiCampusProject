package multi.second.project.infra.util.file.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.infra.code.Code;
import multi.second.project.infra.util.file.FilePath;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class FilePathDto {
	
	private Long fpIdx;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	private LocalDateTime regDate;
	private Boolean isDel;
	private String groupName;

	public String getFullPath() {
		return Code.STORAGE_PATH + groupName + "/" + savePath + renameFileName;
	}
	
	public FilePathDto(FilePath entity) {
		this.fpIdx = entity.getFpIdx();
		this.originFileName = entity.getOriginFileName();
		this.renameFileName = entity.getRenameFileName();
		this.savePath = entity.getSavePath();
		this.regDate = entity.getRegDate();
		this.isDel = entity.getIsDel();
		this.groupName = entity.getGroupName();
	}
	
	public static List<FilePathDto> toDtoList(List<FilePath> entity) {
		return entity.stream()
				.map(e -> new FilePathDto(e))
				.collect(Collectors.toList());
	}
	
}


//Gallery 엔티티에서 사용할 정보(postIdx, userId, title, regDate, thumb)를 가져와서 DTO 클래스에 매핑하는데,
// 이때 생성자를 이용하여 객체를 생성합니다.
// 생성자에서는 Gallery 엔티티에서 가져온 정보를 각각의 변수에 매핑합니다


// GalleryListResponse 클래스에서는 Gallery 엔티티 리스트를 DTO 리스트로 변환하는 toDtoList() 메소드를 제공합니다.
// 이 메소드는 Gallery 엔티티 리스트를 인자로 받아, 각 엔티티를 DTO로 변환하여 리스트로 반환합니다.
// 이때, stream(), map(), collect() 함수를 이용하여 간단하게 변환할 수 있습니다.

//	데이터 전송 객체(DTO) 패턴을 적용하여, 엔티티와 프레젠테이션 계층 간의 데이터 전환을 보다 효율적으로 처리하기 위한 것입니다.





