package multi.second.project.note;


import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.note.domain.Note;
import multi.second.project.domain.note.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest
//@AutoConfigureMockMvc
//public class NoteServiceTest {
//
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    private NoteRepository noteRepository;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//
//    @Test
//    public void testCreateNote(){
//
//
//          Member me = memberRepository.findById("guswnd1212").get();
//        Member you = memberRepository.findById("guswnd1380").get();
//          Note note = Note.builder().member(me).build();
//          noteRepository.save(note);
//
//        Partner partner1 = Partner.builder().member(me).build();
//        partnerRepository.save(partner1);
//        note.addPartner(partner1);
//
//        Partner partner2 = Partner.builder().member(you).build();
//      partnerRepository.save(partner2);
//        note.addPartner(partner2);
//        noteRepository.save(note);
//
//    }
//
//    @Test
//    public void testPartnerAdd(){
//
//        Note note = noteRepository.findById(2L).get();
//
//        Member member = memberRepository.findById("guswnd0127").get();
//        Partner partner = Partner.builder().member(member).build();
//        partnerRepository.save(partner);
//        note.addPartner(partner);
//
//        noteRepository.save(note);
//
//
//
//    }
//
//
//
//
//
//}
