package multi.second.project.domain.note.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NoteRequest {
    private String userId;
    private List<String> memberIds;
}
