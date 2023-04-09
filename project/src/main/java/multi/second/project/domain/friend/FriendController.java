//package multi.second.project.domain.friend;
//
//import lombok.AllArgsConstructor;
//import multi.second.project.domain.friend.domain.Friend;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@AllArgsConstructor
//@RequestMapping("firends")
//public class FriendController {
//
//    private final FriendService friendService;
//
//    @GetMapping
//    public String getFriends(Model model) {
//        List<Friend> friends = friendService.getAllFriends();
//        model.addAttribute("friends", friends);
//        return "friends/friends";
//    }
//
//    @GetMapping("/add")
//    public String addFriendForm(Model model) {
//        model.addAttribute("friend", new Friend());
//        return "friends/addFriendForm";
//    }
//
//    @PostMapping("/add")
//    public String addFriendSubmit(@ModelAttribute Friend friend) {
//        friendService.saveFriend(friend);
//        return "redirect:/friends";
//    }
//
////    @GetMapping("/edit/{id}")
////    public String editFriendForm(@PathVariable Long id, Model model) {
////        Optional<Friend> friend = friendService.getFriendById(id);
////        model.addAttribute("friend", friend.orElseThrow(() -> new IllegalArgumentException("Invalid friend id:" + id)));
////        return "friends/editFriendForm";
////    }
////
////    @PostMapping("/edit/{id}")
////    public String editFriendSubmit(@PathVariable Long id, @ModelAttribute Friend friend) {
////        friend.setId(id);
////        friendService.saveFriend(friend);
////        return "redirect:/friends";
////    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteFriend(@PathVariable Long id) {
//        friendService.deleteFriendById(id);
//        return "redirect:/friends";
//    }
//}