package capgemini.challenge.api.service.interfaces;

import capgemini.api.openapi.dto.UserStory;

import java.net.URI;
import java.util.List;

public interface IUserStoryService {
    List<UserStory> getAllUserStories(String status);

    UserStory getUserStoryById(Long userStoryId);

    UserStory patchUserStoryStatus(UserStory userStory, String status);

    UserStory addUserStory(UserStory userStory);

    UserStory updateUserStory(UserStory userStory);
}
