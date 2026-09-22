package goit.ua.mynotespet.notes.repository;

import goit.ua.mynotespet.notes.entity.Note;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepositoryImplementation<Note, Long> {
    List<Note> findAllByUserUsername(String username);
}
