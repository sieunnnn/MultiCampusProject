package multi.second.project.domain.profile;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import multi.second.project.domain.profile.dto.request.ProfileModifyRequest;

import lombok.AllArgsConstructor;
import multi.second.project.domain.member.UserPrincipal;
import multi.second.project.domain.profile.domain.Profile;
import multi.second.project.domain.profile.service.ProfileService;

@Controller
@AllArgsConstructor
@RequestMapping("profile")
public class ProfileController {

	private final ProfileService profileService;

    @GetMapping("profile")
    public String index(//HttpSession session,
    		Model model, HttpServletResponse response) throws IOException {
        //Principal principal = (Principal)session.getAttribute("auth");
        //if(principal != null){
            //model.addAttribute("data", principal.getUserId());
    		//System.out.println("UserPrincipal.getUserPrincipal().getPrincipal().getUserId() : " +UserPrincipal.getUserPrincipal().getPrincipal().getUserId());
            //model.addAttribute("data", UserPrincipal.getUserPrincipal().getPrincipal().getUserId());
            Profile profile = profileService.getProfileData(UserPrincipal.getUserPrincipal().getPrincipal().getUserId());
            model.addAttribute("profile", profile);
//            System.out.println("profile :" +profile);
//            if(profile != null){
//                String imagePath = "/img/image/"+ profile.getImagePath();
//                System.out.println(imagePath);
//                model.addAttribute("imageUrl", imagePath);
//            }
        //}

        return "profile/index";
    }

//    @GetMapping("/modify")
//    public String modify(){
//        return "profile/modify";
//    }
//
//    @PostMapping("/modify")
//    public String redirect(){
//        return "redirect:/profile";
//    }

    //@ResponseBody
    @PostMapping("saveImg")
    public String saveProfileImage(//HttpSession session, 
    		HttpServletRequest req, Model model, ProfileModifyRequest dto) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)req;
        //req 로 넘어오는 데이터를 MultipartHttpServletRequest 로 변령을 해준다
        MultipartFile file = multipartHttpServletRequest.getFile("uploadFile");
        System.out.println("file.getName() : "+file.getName());
        //이제 여기에서 앞에서 보낸 formData 의 key 값을 적어주면 해당 파일을 뽑아 올 수 있는 것이다

        //Principal principal = (Principal)session.getAttribute("auth");

        //Profile result = profileService.saveProfileImage(file, principal.getUserId());
        profileService.saveProfileImage(file, UserPrincipal.getUserPrincipal().getUserId(), dto);
//        System.out.println("사진 업로드");
        //model.addAttribute("data", principal.getUserId());
        //model.addAttribute("data", UserPrincipal.getUserPrincipal().getUserId());
        //String imagePath = "/img/image/"+ result.getImagePath();
        //model.addAttribute("imagePath", imagePath);
        //return result.getImagePath();
        return "redirect:/profile/profile";
    }
}
