package capgemini.challenge.api.repository;

import capgemini.challenge.api.model.UserStoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserStoryRepository extends JpaRepository<UserStoryEntity, Long> {
    List<UserStoryEntity> findALLByStatus(String status);
}
