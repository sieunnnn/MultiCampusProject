package multi.second.project.domain.note;

import lombok.AllArgsConstructor;
import multi.second.project.domain.gallery.GalleryService;
import multi.second.project.domain.note.dto.request.NoteRegistRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("note")
public class NoteController {

    private final NoteService noteService;
    @GetMapping("form")
    public String noteForm() {
        return"/board/board-form";
    }


}
