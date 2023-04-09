package multi.second.project.domain.note;

import lombok.AllArgsConstructor;
import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.gallery.dto.request.GalleryRegistRequest;
import multi.second.project.domain.gallery.dto.response.GalleryListResponse;
import multi.second.project.domain.member.MemberRepository;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.note.domain.Note;
import multi.second.project.domain.note.dto.request.NoteRegistRequest;
import multi.second.project.domain.note.dto.response.NoteListResponse;
import multi.second.project.domain.note.repository.NoteRepository;
import multi.second.project.infra.util.file.FilePath;
import multi.second.project.infra.util.file.dto.FilePathDto;
import multi.second.project.infra.util.file.dto.FileUploadDto;
import multi.second.project.infra.util.paging.Paging;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final MemberRepository memberRepository;

    public Map<String, Object> findNoteListByMemberUserId(String userId, Pageable pageable) {
        Page<Note> page = noteRepository.findNoteListByMemberUserId(userId,pageable);
        //  System.out.println("noteRepository.findByMemberUserId(userId,pageable) :  "+page);
        Paging paging = Paging.builder()
                .page(page)
                .blockCnt(5)
                .build();
//        System.out.println("page.getContent() :  " +page.getContent());
//        System.out.println("NoteListResponse.toDtoList(page.getContent()) :  "+ NoteListResponse.toDtoList(page.getContent()));
        return Map.of("noteList", NoteListResponse.toDtoList(page.getContent()), "paging", paging);
    }

    @Transactional
    public void createNote(NoteRegistRequest dto) {
        Member member = memberRepository.findById(dto.getHost()).get();
        Note note = Note.createNote(dto, member);

        // JPA가 변경된 내용을 데이터베이스에 반영
        noteRepository.saveAndFlush(note);

    }

//    public void findNoteByNtIdx(long ntIdx) {
//    }

}
