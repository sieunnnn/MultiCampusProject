package multi.second.project.domain.dashboard;

import lombok.RequiredArgsConstructor;
import multi.second.project.domain.member.UserPrincipal;
import multi.second.project.domain.profile.domain.Profile;
import multi.second.project.domain.profile.dto.request.ProfileModifyRequest;
import multi.second.project.domain.profile.service.ProfileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.charset.Charset;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class DashboardController {

	private final ProfileService profileService;

	@GetMapping
	public String dashboard(Model model) throws IOException {

		Profile profile = profileService.getProfileData(UserPrincipal.getUserPrincipal().getPrincipal().getUserId());
		model.addAttribute("profile", profile);

		return "dashBoard";
	}

	@GetMapping("download")
	public ResponseEntity<FileSystemResource> downloadFile(@RequestParam Long pfIdx){

		ProfileModifyRequest dto = profileService.findImagePathByPfIdx(pfIdx);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDisposition(ContentDisposition.builder("attachment")
				.filename(dto.getImagePath(), Charset.forName("utf-8"))
				.build());

		FileSystemResource fsr = new FileSystemResource(dto.getFullPath());
		return ResponseEntity.ok().headers(headers).body(fsr);
	}
	
}
