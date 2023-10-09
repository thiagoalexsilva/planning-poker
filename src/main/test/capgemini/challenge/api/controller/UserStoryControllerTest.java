package capgemini.challenge.api.controller;

import capgemini.api.openapi.dto.UserStory;
import capgemini.api.openapi.dto.UserStoryStatusEnum;
import capgemini.challenge.api.service.UserStoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static capgemini.api.openapi.dto.UserStoryStatusEnum.*;

@ExtendWith(SpringExtension.class)
class UserStoryControllerTest {

    @Mock
    private UserStoryService userStoryService;
    @InjectMocks
    private UserStoryController userStoryController;

    @Test
    void getUserStories() {
        final var userStory = new UserStory();

        Mockito.when(this.userStoryService.getAllUserStories(Mockito.anyString())).thenReturn(List.of(userStory));

        final var response = this.userStoryController.getUserStories("VOTING");

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertFalse(response.getBody().isEmpty());
    }

    @Test
    void getUserStory_USExists_ReturnsList() {
        final var userStory = new UserStory().userStoryId(1L);

        Mockito.when(this.userStoryService.getUserStoryById(Mockito.anyLong())).thenReturn(userStory);

        final var response = this.userStoryController.getUserStory(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void getUserStory_USNotExists_ReturnsNotFound() {
        Mockito.when(this.userStoryService.getUserStoryById(Mockito.anyLong())).thenReturn(null);

        final var response = this.userStoryController.getUserStory(1L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void patchUserStoryStatus_USExists_ReturnsUS() {
        final var userStory = new UserStory().userStoryId(1L);
        UserStoryStatusEnum userStoryStatusEnum = new UserStoryStatusEnum();
        userStoryStatusEnum.setStatus(StatusEnum.VOTING);

        Mockito.when(this.userStoryService.getUserStoryById(Mockito.anyLong())).thenReturn(userStory);
        Mockito.when(this.userStoryService.patchUserStoryStatus(Mockito.any(UserStory.class), Mockito.anyString())).thenReturn(userStory);

        final var response = this.userStoryController.patchUserStoryStatus(1L, userStoryStatusEnum);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void patchUserStoryStatus_USNotExists_ReturnsNotFound() {
        Mockito.when(this.userStoryService.getUserStoryById(Mockito.anyLong())).thenReturn(null);

        final var response = this.userStoryController.patchUserStoryStatus(1L, Mockito.any(UserStoryStatusEnum.class));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}