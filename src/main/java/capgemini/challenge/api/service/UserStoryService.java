package capgemini.challenge.api.service;

import capgemini.api.openapi.dto.UserStory;
import capgemini.challenge.api.exception.PlanningPokerException;
import capgemini.challenge.api.mapper.MapStructMapper;
import capgemini.challenge.api.model.UserStoryEntity;
import capgemini.challenge.api.repository.IUserStoryRepository;
import capgemini.challenge.api.service.interfaces.IUserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserStoryService implements IUserStoryService {
    private final IUserStoryRepository userStoryRepository;
    private final MapStructMapper mapper;

    @Autowired
    public UserStoryService(final IUserStoryRepository userStoryRepository,
                            final MapStructMapper mapper) {
        this.userStoryRepository = userStoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserStory> getAllUserStories(String status) {
        ArrayList<UserStory> userStories = new ArrayList<>();
        ArrayList<UserStoryEntity> userStoryEntities = new ArrayList<>();

        if(Objects.isNull(status)){
            userStoryEntities.addAll(this.userStoryRepository.findAll());
        } else {
            userStoryEntities.addAll(this.userStoryRepository.findALLByStatus(status));
        }

        if(!userStoryEntities.isEmpty()) {
            userStoryEntities.forEach(userStoryEntity -> userStories.add(this.mapper.userStoryEntityToUserStory(userStoryEntity)));
        }

        return userStories;
    }

    @Override
    public UserStory getUserStoryById(Long userStoryId) {
        UserStoryEntity userStoryEntity = this.userStoryRepository.findById(userStoryId)
                .orElseThrow(PlanningPokerException::new);
        return this.mapper.userStoryEntityToUserStory(userStoryEntity);
    }

    @Override
    public UserStory patchUserStoryStatus(UserStory userStory, String status) {
        userStory.setStatus(UserStory.StatusEnum.valueOf(status));
        UserStoryEntity userStoryEntity = this.mapper.userStoryToUserStoryEntity(userStory);
        return this.mapper.userStoryEntityToUserStory(this.userStoryRepository.save(userStoryEntity));
    }

    @Override
    public UserStory addUserStory(UserStory userStory) {
        UserStoryEntity userStoryEntity = this.mapper.userStoryToUserStoryEntity(userStory);
        return this.mapper.userStoryEntityToUserStory(this.userStoryRepository.save(userStoryEntity));
    }

    @Override
    public UserStory updateUserStory(UserStory userStory) {
        UserStoryEntity userStoryEntity = this.mapper.userStoryToUserStoryEntity(userStory);
        return this.mapper.userStoryEntityToUserStory(this.userStoryRepository.save(userStoryEntity));
    }
}
