package capgemini.challenge.api.service;

import capgemini.api.openapi.dto.UserStory;
import capgemini.challenge.api.mapper.MapStructMapper;
import capgemini.challenge.api.model.UserStoryEntity;
import capgemini.challenge.api.repository.IUserStoryRepository;
import capgemini.challenge.api.service.interfaces.IUserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<UserStoryEntity> userStoryEntities = this.userStoryRepository.findALLByStatus(status);

        if(!userStoryEntities.isEmpty()) {
            userStoryEntities.forEach(userStoryEntity -> userStories.add(this.mapper.userStoryEntityToUserStory(userStoryEntity)));
        }

        return userStories;
    }

    @Override
    public UserStory getUserStoryById(Long userStoryId) {
        UserStoryEntity userStoryEntity = this.userStoryRepository.findById(userStoryId)
                .orElseThrow(RuntimeException::new);
        return this.mapper.userStoryEntityToUserStory(userStoryEntity);
    }

    @Override
    public UserStory patchUserStoryStatus(UserStory userStory, String status) {
        userStory.setStatus(UserStory.StatusEnum.valueOf(status));
        UserStoryEntity userStoryEntity = this.mapper.userStoryToUserStoryEntity(userStory);
        return this.mapper.userStoryEntityToUserStory(this.userStoryRepository.save(userStoryEntity));
    }
}
