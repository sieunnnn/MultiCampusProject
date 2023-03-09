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
//
//    @GetMapping(value = "/")
//    public String Profile() {
//        return "profile1/profile";
//   }
//
    @GetMapping(value = "/")
    public String friends() {
        return "messages1/friends";
    }

    @GetMapping(value = "/messages")
    public String messages() {
        return "messages1/messages";
    }

    @GetMapping(value = "/add/friends")
    public String addFriends() {
        return "messages1/add_friends";
    }

    @GetMapping(value = "/delete/friends")
    public String deleteFriends() {
        return "messages1/delete_friends";
    }
}
