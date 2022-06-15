package sk.cybersoft.cybernotes.cybernotesspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.cybersoft.cybernotes.cybernotesspring.entity.AccountEntity;

@Repository
public interface UserRepository extends JpaRepository<AccountEntity, Long> {
}
