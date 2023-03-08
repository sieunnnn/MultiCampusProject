package multi.second.project.domain.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping(value = "/")
    public String login() {
        return "member1/login";
    }
}
