package multi.second.project.domain.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {

//    @GetMapping(value = "/")
//    public String layout() {
//        return "main_layout";
//    }

//    @GetMapping(value = "/")
//    public String test() {
//        return "test";
//    }

//    @GetMapping(value = "/")
//    public String member() {
//        return "member1/login";
//    }

    @GetMapping(value = "/")
    public String Profile() {
        return "profile1/profile";
    }
}
