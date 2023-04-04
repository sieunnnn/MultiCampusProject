package multi.second.project.domain.note.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import multi.second.project.domain.note.domain.Note;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
public class NoteListResponse {

    private Long ntIdx;
    private List<PartnerResponse> partnerResponse = new ArrayList<>();
    //    private String lastMassage;
    public NoteListResponse(Note note) {
        this.ntIdx = note.getNtIdx();
        this.partnerResponse =  PartnerResponse.toDtoList(note.getPartners());
    }

    public static List<NoteListResponse> toDtoList(List<Note> entityList){
        return entityList.stream().map(e -> new NoteListResponse(e)).collect(toList());
    }
}

