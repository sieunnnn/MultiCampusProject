package multi.second.project.domain.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {
//
    @GetMapping(value = "/dashboard")
    public String layout() {
        return "main_layout";
    }
//
////    @GetMapping(value = "/")
////    public String test() {
////        return "test";
////    }
//
//    @GetMapping(value = "/login")
//    public String member() {
//        return "member1/login";
//    }
//
//    @GetMapping(value = "/profile")
//    public String Profile() {
//        return "profile1/profile";
//   }
//
//    @GetMapping(value = "/friends")
//    public String friends() {
//        return "messages1/friends";
//    }
//
//    @GetMapping(value = "/messages")
//    public String messages() {
//        return "messages1/messages";
//    }
//
//    @GetMapping(value = "/add/friends")
//    public String addFriends() {
//        return "messages1/add";
//    }
//
//    @GetMapping(value = "/delete/friends")
//    public String deleteFriends() {
//        return "messages1/delete";
//    }
//
//    @GetMapping(value = "/gallery")
//    public String gallery() {
//        return "gallery1/list";
//    }
//
//    @GetMapping(value = "/gallery/detail")
//    public String galleryDetail() {
//        return "gallery1/detail";
//    }
//
//    @GetMapping(value = "/gallery/modify")
//    public String galleryModify() {
//        return "gallery1/modify";
//    }
//
//    @GetMapping(value = "/gallery/add")
//    public String galleryAdd() {
//        return "gallery1/add";
//    }
//
//    @GetMapping(value = "/")
//    public String home() {
//        return "home";
//    }

}
