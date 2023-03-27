package multi.second.project.domain.note;

import multi.second.project.domain.member.MemberRepository;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.note.domain.Note;
import multi.second.project.domain.note.dto.request.NoteRequest;
import multi.second.project.domain.note.repository.NoteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;
    private final MemberRepository memberRepository;

    public NoteService(NoteRepository noteRepository, MemberRepository memberRepository) {
        this.noteRepository = noteRepository;
        this.memberRepository = memberRepository;
    }

    public void createNote(NoteRequest noteRequest) throws Exception {
        List<Member> members = new ArrayList<>();
        for (String memberId : noteRequest.getMemberIds()) {
            Optional<Member> optionalMember = memberRepository.findById(memberId);
            if (optionalMember.isPresent()) {
                members.add(optionalMember.get());
            } else {
                throw new Exception(memberId);
            }
        }
        Note note = Note.builder()
                .members(members)
                .isDel(false)
                .build();
        noteRepository.save(note);
    }
}
