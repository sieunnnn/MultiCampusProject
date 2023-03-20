package multi.second.project.domain.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chatGET(HttpSession session) {
        System.out.println("@ChatController, chat GET()");
        return "chat";
    }

}
