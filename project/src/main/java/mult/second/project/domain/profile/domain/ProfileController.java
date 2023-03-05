package mult.second.project.domain.profile.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping
    public String index(){
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
}
