package multi.second.project.infra.util.file;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import multi.second.project.infra.code.Code;
import multi.second.project.infra.code.ErrorCode;
import multi.second.project.infra.exception.HandlableException;
import multi.second.project.infra.util.file.dto.FilePathDto;
import multi.second.project.infra.util.file.dto.FileUploadDto;

@Component
@RequiredArgsConstructor
public class FileUtil {
	
	
	public List<FileUploadDto> generateFileUploadDtos(String groupName, List<MultipartFile> files){
		//generateFileUploadDtos() 메서드는 파일 업로드시에 업로드된 파일의 정보를 담은 List를 반환하는 메서드입니다.
		List<FileUploadDto> fileInfos = new ArrayList<FileUploadDto>();
		//generateFileUploadDtos() 메서드 fileInfos 반환한다

		// 2. 파일 업로드
		for (MultipartFile multipartFile : files) {
			
			if(multipartFile.isEmpty()) continue;
			
			FilePathDto file = new FilePathDto();
			//FileUploadDto안에서 FilePathDto 타입의 객체를 선언한다

			String uploadPath = createUploadPath(groupName);
			String originFileName = multipartFile.getOriginalFilename();
			String renameFileName = createRenameFileName(originFileName);
			
			file.setGroupName(groupName);
			file.setOriginFileName(originFileName);
			file.setRenameFileName(renameFileName);
			file.setSavePath(uploadPath);
			
			fileInfos.add(FileUploadDto.builder()
							.filePathDto(file)
							.multipartFile(multipartFile).build());
		}
		
		return fileInfos;
		
	}
	
	public void uploadFile(List<FileUploadDto> fileUploadDtos) {
		
		// 2. 파일 업로드
		for (FileUploadDto dto : fileUploadDtos) {		
			transferFile(dto.getMultipartFile(), dto.getFilePathDto());
		}
		
	}
	
	public void deleteFile(FilePathDto filePathDto) {
		File file = new File(filePathDto.getFullPath());
		file.delete();
	}
	
	private File transferFile(MultipartFile multipartFile, FilePathDto fileInfo) {
		
		File dest = new File(fileInfo.getFullPath());
		 
		 try {
			multipartFile.transferTo(dest);
		} catch (IllegalStateException | IOException e) {
			throw new HandlableException(ErrorCode.FAILED_UPLOAD_FILE, e);
		}
		 
		return dest;
	}
	
	private String createUploadPath(String groupName) {
		// 폴더를 기능, 일자별로 생성, 지나치게 많은 파일이 저장된 폴더는 열리지 않음
		LocalDate now =  LocalDate.now();
		String uploadPath = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth() + "/";
		
		File file = new File(Code.STORAGE_PATH + groupName + "/" + uploadPath);
		
		file.mkdirs();
		return uploadPath;
	}
	
	private String createRenameFileName(String originFileName) {
		// 파일이름을 유니크하게 변경, 만약 다른 사용자가 같은 이름의 파일을 업로드하면 덮어써지거나, 에러가 나기 때문
		String subfix = "";
		
		if(originFileName.contains(".")){
			subfix = originFileName.substring(originFileName.lastIndexOf("."));
		}

		return UUID.randomUUID().toString().substring(0, 8) + subfix; 
	}

	
	

}
