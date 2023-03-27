package multi.second.project.domain.note.repository;

import multi.second.project.domain.note.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
