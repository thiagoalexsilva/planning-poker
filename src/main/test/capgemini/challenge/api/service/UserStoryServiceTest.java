package capgemini.challenge.api.service;

import capgemini.api.openapi.dto.UserStory;
import capgemini.challenge.api.mapper.MapStructMapper;
import capgemini.challenge.api.model.UserStoryEntity;
import capgemini.challenge.api.repository.IUserStoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class UserStoryServiceTest {

    @Mock
    private IUserStoryRepository userStoryRepository;
    @Mock
    private MapStructMapper mapper;
    @InjectMocks
    private UserStoryService userStoryService;

    @Test
    void getAllUserStories_USExists_ReturnsList(){
        final var userStoryEntityList = List.of(new UserStoryEntity());
        final var userStory = new UserStory();

        Mockito.when(this.userStoryRepository.findALLByStatus(Mockito.anyString())).thenReturn(userStoryEntityList);
        Mockito.when(this.mapper.userStoryEntityToUserStory(Mockito.any(UserStoryEntity.class))).thenReturn(userStory);

        final var response = this.userStoryService.getAllUserStories("PENDING");

        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    void getAllUserStories_USNotExists_ReturnsEmptyList(){

        Mockito.when(this.userStoryRepository.findALLByStatus(Mockito.anyString())).thenReturn(List.of());

        final var response = this.userStoryService.getAllUserStories("PENDING");

        Assertions.assertTrue(response.isEmpty());
    }

    @Test
    void getUserStoryById_USNotExists_ThrowsException(){

        Mockito.when(this.userStoryRepository.findById(Mockito.anyLong())).thenReturn(null);

        Assertions.assertThrows(RuntimeException.class, () -> this.userStoryService.getUserStoryById(1L));
    }

    @Test
    void getUserStoryById_USExists_ReturnsUS(){
        final var userStoryEntity = Optional.of(new UserStoryEntity());
        final var userStory = new UserStory();

        Mockito.when(this.userStoryRepository.findById(Mockito.anyLong())).thenReturn(userStoryEntity);
        Mockito.when(this.mapper.userStoryEntityToUserStory(Mockito.any(UserStoryEntity.class))).thenReturn(userStory);

        final var response = this.userStoryService.getUserStoryById(1L);

        Assertions.assertNotNull(response);
    }

    @Test
    void patchUserStoryStatus_USExists_ReturnsUS(){
        final var userStoryEntity = new UserStoryEntity();
        final var userStory = new UserStory();

        Mockito.when(this.userStoryRepository.save(Mockito.any(UserStoryEntity.class))).thenReturn(userStoryEntity);
        Mockito.when(this.mapper.userStoryEntityToUserStory(Mockito.any(UserStoryEntity.class))).thenReturn(userStory);
        Mockito.when(this.mapper.userStoryToUserStoryEntity(Mockito.any(UserStory.class))).thenReturn(userStoryEntity);

        final var response = this.userStoryService.patchUserStoryStatus(userStory, "VOTING");

        Assertions.assertNotNull(response);
    }
}