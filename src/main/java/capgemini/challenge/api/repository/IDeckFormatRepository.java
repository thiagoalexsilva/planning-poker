package capgemini.challenge.api.repository;

import capgemini.challenge.api.model.DeckFormatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeckFormatRepository extends JpaRepository<DeckFormatEntity, Long> {
}
