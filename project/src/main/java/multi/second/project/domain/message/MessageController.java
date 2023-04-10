package multi.second.project.domain.message;

import lombok.AllArgsConstructor;
import multi.second.project.domain.member.MemberRepository;
import multi.second.project.domain.member.UserPrincipal;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.message.domain.Message;
import multi.second.project.domain.message.dto.request.MessageRegistRequest;
import multi.second.project.domain.message.dto.response.MessageListResponse;
import multi.second.project.domain.note.domain.Note;
import multi.second.project.domain.note.repository.NoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;
    private final NoteRepository noteRepository;
    private final MemberRepository memberRepository;
    @GetMapping("/{ntIdx}")
    public String messageList(
            @PathVariable("ntIdx") Long ntIdx,
            @PageableDefault(size=10, sort="msIdx", direction = Sort.Direction.DESC, page = 0)
            Pageable pageable,
            Model model
    ) {
        Note note = noteRepository.findById(ntIdx).get();
        model.addAttribute("note", note);
        String userId = UserPrincipal.getUserPrincipal().getPrincipal().getUserId();
        model.addAttribute("userId", userId);

        Map<String, Object> commandMap = messageService.findMessageListByNoteNtIdx(ntIdx, pageable);
        model.addAllAttributes(commandMap);

        return "message/message-list";
    }

    @PostMapping("/{ntIdx}/write")
    public String writeMessage(
            @PathVariable("ntIdx") Long ntIdx,
            MessageRegistRequest dto
    ) {
        Member member = memberRepository.findById(dto.getUserId()).get();
        messageService.createMessage(dto, member, ntIdx);
        return "redirect:/message/" + ntIdx;
    }

}