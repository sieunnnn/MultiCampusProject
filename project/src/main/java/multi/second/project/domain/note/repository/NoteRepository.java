package multi.second.project.domain.note.repository;

import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.note.domain.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

   // Page<Note> findByMembersUserId(String userId, Pageable pageable);
   // Page<Note> findByPartnersMemberUserId(String userId, Pageable pageable);

    Page<Note> findNoteListByMemberUserId(String userId, Pageable pageable);
}
