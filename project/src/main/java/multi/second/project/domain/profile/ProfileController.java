package multi.second.project.domain.profile;

import multi.second.project.domain.member.dto.Principal;
import multi.second.project.domain.profile.domain.Profile;
import multi.second.project.domain.profile.dto.ProfileDTO;
import multi.second.project.domain.profile.service.ProfileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping
    public String index(HttpSession session, Model model, HttpServletResponse response) throws IOException {
        Principal principal = (Principal)session.getAttribute("auth");
        if(principal != null){
            model.addAttribute("data", principal.getUserId());
            Profile profile = profileService.getProfileData(principal.getUserId());
            System.out.println(profile);
            if(profile != null){
                String imagePath = "/img/image/"+ profile.getImagePath();
                System.out.println(imagePath);
                model.addAttribute("imageUrl", imagePath);
            }
        }

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

    @ResponseBody
    @PostMapping("/saveImg")
    public String saveProfileImage(HttpServletRequest req, HttpSession session, Model model) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)req;
        //req 로 넘어오는 데이터를 MultipartHttpServletRequest 로 변령을 해준다
        MultipartFile file = multipartHttpServletRequest.getFile("uploadFile");
        //이제 여기에서 앞에서 보낸 formData 의 key 값을 적어주면 해당 파일을 뽑아 올 수 있는 것이다

        Principal principal = (Principal)session.getAttribute("auth");

        Profile result = profileService.saveProfileImage(file, principal.getUserId());
        System.out.println("사진 업로드");
        model.addAttribute("data", principal.getUserId());
        String imagePath = "/img/image/"+ result.getImagePath();
        model.addAttribute("imagePath", imagePath);
        return result.getImagePath();
    }
}
