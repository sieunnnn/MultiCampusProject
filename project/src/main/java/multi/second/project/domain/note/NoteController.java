package multi.second.project.domain.note;

import lombok.AllArgsConstructor;
import multi.second.project.domain.gallery.GalleryService;
import multi.second.project.domain.member.UserPrincipal;
import multi.second.project.domain.note.dto.request.NoteRegistRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("note")
public class NoteController {

    private final NoteService noteService;
    @GetMapping("form")
    public String noteForm() {
        return"/";
    }


    @GetMapping("list")
    public String noteList(
                              @PageableDefault(size=10, sort="ntIdx", direction = Sort.Direction.DESC, page = 0)
                              Pageable pageable,
                              Model model

    ) {

        Map<String, Object> commandMap = noteService.findNoteListByUserId(UserPrincipal.getUserPrincipal().getPrincipal().getUserId(),pageable);
        System.out.println("galleryService.findGalleryListByUserId(principal.getUserId(),pageable) : "+commandMap);
        model.addAllAttributes(commandMap);

        return "note/note-list";
    }

}
