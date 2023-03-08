package multi.second.project.domain.message;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {

    @GetMapping(value="/")
    public String message() {
        return "message/list";
    }

    @GetMapping(value = "message/add_friends")
    public String addFriends() {
        return "message/add_friends";
    }

    @GetMapping(value = "message/delete_friends")
    public String deleteFriends() {
        return "message/delete_friends";
    }
}
