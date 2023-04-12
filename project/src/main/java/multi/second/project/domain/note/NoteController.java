package multi.second.project.domain.note;

import lombok.AllArgsConstructor;
import multi.second.project.domain.gallery.dto.request.GalleryRegistRequest;
import multi.second.project.domain.member.UserPrincipal;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.note.domain.Note;
import multi.second.project.domain.note.dto.request.NoteRegistRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("note")
public class NoteController {

    private final NoteService noteService;

    @GetMapping("form")
    public String noteForm(Model model) {
        return"/";
    }

    @PostMapping("upload")
    public String upload(NoteRegistRequest dto)
    {
//        dto.setUserId(UserPrincipal.getUserPrincipal().getUserId()); //로그인한 사람의 아이디를 받아 저장
        noteService.createNote(dto); // 받은 정보와 파일들로 서비스에서 등록처리

        return "redirect:/note/list";
    }

//    @PostMapping("upload")
//    public String upload(NoteRegistRequest dto)
//    {
//        dto.setUserId(UserPrincipal.getUserPrincipal().getUserId()); //로그인한 사람의 아이디를 받아 저장
//        noteService.createNote(dto); // 받은 정보와 파일들로 서비스에서 등록처리
//
//        return "redirect:/note/list";
//    }



    @GetMapping("list")
    public String noteList(
            @PageableDefault(size=10, sort="ntIdx", direction = Sort.Direction.DESC, page = 0)
            Pageable pageable,
            Model model

    ) {

        String userId = UserPrincipal.getUserPrincipal().getPrincipal().getUserId();
        model.addAttribute("userId", userId);

        Map<String, Object> commandMap = noteService.findNoteListByMemberUserId(userId, pageable);
        model.addAllAttributes(commandMap);

        return "note/note-list";
    }

    @GetMapping("{ntIdx}")
    public String noteDetail(@PathVariable("ntIdx") Long ntIdx, Model model) {
            Note note = noteService.findNoteByNtIdx(ntIdx);
            model.addAttribute("note", note);
            return "note/note-detail";


//    public String noteDetail(@PathVariable("ntIdx") long ntIdx, Model model) {
//
//        // ntIdx로 note 조회
//        noteService.findNoteByNtIdx(ntIdx);
//        //note가 조회되면 List<Message> 조회가 되겠지지
//
//        model.addAttribute("note", note);

    }

}
