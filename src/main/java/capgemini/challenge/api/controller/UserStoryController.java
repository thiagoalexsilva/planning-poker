package capgemini.challenge.api.controller;

import capgemini.api.openapi.api.UserStoryApi;
import capgemini.api.openapi.dto.UserStory;
import capgemini.api.openapi.dto.UserStoryStatusEnum;
import capgemini.challenge.api.service.interfaces.IUserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class UserStoryController implements UserStoryApi {

    private final IUserStoryService userStoryService;

    @Autowired
    public UserStoryController (final IUserStoryService userStoryService){
        this.userStoryService = userStoryService;
    }

    @Override
    public ResponseEntity<List<UserStory>> getUserStories(String status) {
        return ResponseEntity.ok(this.userStoryService.getAllUserStories(status));
    }

    @Override
    public ResponseEntity<UserStory> addUserStory(UserStory userStory) {
        return new ResponseEntity(this.userStoryService.addUserStory(userStory), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserStory> getUserStory(Long userStoryId) {
        UserStory userStory = this.userStoryService.getUserStoryById(userStoryId);

        if(Objects.isNull(userStory)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userStory);
    }

    @Override
    public ResponseEntity<UserStory> patchUserStoryStatus(Long userStoryId, UserStoryStatusEnum userStoryStatusEnum) {
        UserStory userStory = this.userStoryService.getUserStoryById(userStoryId);

        if(Objects.isNull(userStory)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(this.userStoryService.patchUserStoryStatus(userStory, userStoryStatusEnum.getStatus().getValue()));
    }

    @Override
    public ResponseEntity<UserStory> updateUserStory(Long userStoryId, UserStory userStory) {
        if(Objects.isNull(this.userStoryService.getUserStoryById(userStoryId))){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(this.userStoryService.updateUserStory(userStory));
    }
}
