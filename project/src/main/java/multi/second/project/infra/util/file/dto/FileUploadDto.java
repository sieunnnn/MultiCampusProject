package multi.second.project.infra.util.file.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileUploadDto {
	
	private FilePathDto filePathDto;
	private MultipartFile multipartFile;
	
}
