package capgemini.challenge.api.service;

import capgemini.api.openapi.dto.User;
import capgemini.challenge.api.mapper.MapStructMapper;
import capgemini.challenge.api.model.UserEntity;
import capgemini.challenge.api.model.UserStoryEntity;
import capgemini.challenge.api.repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private IUserRepository userRepository;
    @Mock
    private MapStructMapper mapper;
    @InjectMocks
    private UserService userService;

    @Test
    void getUserById() {
        Mockito.when(this.userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new UserEntity()));
        Mockito.when(this.mapper.userEntityToUser(Mockito.any(UserEntity.class))).thenReturn(new User());

        final var response = this.userService.getUserById(1L);

        Assertions.assertNotNull(response);
    }

    @Test
    void getUserById_UserNotExists_ThrowsException() {

        Mockito.when(this.userRepository.findById(Mockito.anyLong())).thenReturn(null);

        Assertions.assertThrows(RuntimeException.class, () -> this.userService.getUserById(1L));
    }

    @Test
    void createUser() {
        final var userMock = new User();

        Mockito.when(this.mapper.userToUserEntity(Mockito.any(User.class))).thenReturn(new UserEntity());
        Mockito.when(this.userRepository.save(Mockito.any(UserEntity.class))).thenReturn(new UserEntity());
        Mockito.when(this.mapper.userEntityToUser(Mockito.any(UserEntity.class))).thenReturn(new User());

        final var response = this.userService.createUser(userMock);

        Assertions.assertNotNull(response);
    }
}