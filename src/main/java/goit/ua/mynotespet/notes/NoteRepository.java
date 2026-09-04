package goit.ua.mynotespet.notes;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepositoryImplementation<Note, Long> {
}
