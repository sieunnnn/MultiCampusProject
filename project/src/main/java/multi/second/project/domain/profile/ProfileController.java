package multi.second.project.domain.profile;

import multi.second.project.domain.member.dto.Principal;
import multi.second.project.domain.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;
    @GetMapping
    public String index(HttpSession session, Model model){
        Principal principal = (Principal)session.getAttribute("auth");
        if(principal != null){
            System.out.println("princ"+ principal);
            model.addAttribute("data", principal.getUserId());
        }

        return "profile/index";
    }

    @GetMapping("/modify")
    public String modify(){
        return "profile/modify";
    }

    @PostMapping("/modify")
    public String redirect(){
        return "redirect:/profile";
    }

    @ResponseBody
    @PostMapping("/saveImg")
    public int saveProfileImage(HttpServletRequest req) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)req;
        //req 로 넘어오는 데이터를 MultipartHttpServletRequest 로 변령을 해준다
        MultipartFile file = multipartHttpServletRequest.getFile("uploadFile");
        //이제 여기에서 앞에서 보낸 formData 의 key 값을 적어주면 해당 파일을 뽑아 올 수 있는 것이다
        int resultMsg = profileService.uploadFile(file);
        System.out.println("사진 업로드");

        return resultMsg;
    }
}
