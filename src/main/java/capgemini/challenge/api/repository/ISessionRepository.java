package capgemini.challenge.api.repository;

import capgemini.challenge.api.model.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISessionRepository extends JpaRepository<SessionEntity, Long> {
}
