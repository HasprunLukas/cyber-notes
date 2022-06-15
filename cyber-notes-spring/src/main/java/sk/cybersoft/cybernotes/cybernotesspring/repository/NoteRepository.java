package sk.cybersoft.cybernotes.cybernotesspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.cybersoft.cybernotes.cybernotesspring.entity.NoteEntity;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
}
