package multi.second.project.domain.friends;

import multi.second.project.domain.friends.domain.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class FriendController {
    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("friends")
    public String home(Model model) {
        List<Friend> friends = friendService.getAllFriends();
        model.addAttribute("friends", friends);
        return "index";
    }

    @GetMapping("/addFriend")
    public String addFriendForm(Model model) {
        model.addAttribute("friend", new Friend());
        return "addFriendForm";
    }

    @PostMapping("/addFriend")
    public String addFriendSubmit(@ModelAttribute Friend friend) {
        friendService.saveFriend(friend);
        return "redirect:/";
    }

    @GetMapping("/editFriend/{id}")
    public String editFriendForm(@PathVariable Long id, Model model) {
        Optional<Friend> friend = friendService.getFriendById(id);
        model.addAttribute("friend", friend.orElseThrow(() -> new IllegalArgumentException("Invalid friend id:" + id)));
        return "editFriendForm";
    }

    @PostMapping("/editFriend/{id}")
    public String editFriendSubmit(@PathVariable Long id, @ModelAttribute Friend friend) {
        friend.setId(id);
        friendService.saveFriend(friend);
        return "redirect:/";
    }

    @GetMapping("/deleteFriend/{id}")
    public String deleteFriend(@PathVariable Long id) {
        friendService.deleteFriendById(id);
        return "redirect:/";
    }
}