package multi.second.project.domain.note;

import multi.second.project.domain.note.dto.request.NoteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createNote(@RequestBody NoteRequest noteRequest) throws Exception {
        noteService.createNote(noteRequest);
        return ResponseEntity.ok().build();
    }
}
