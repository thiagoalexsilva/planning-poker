package capgemini.challenge.api.service;

import capgemini.api.openapi.dto.Session;
import capgemini.api.openapi.dto.User;
import capgemini.api.openapi.dto.UserStory;
import capgemini.challenge.api.exception.PlanningPokerException;
import capgemini.challenge.api.mapper.MapStructMapper;
import capgemini.challenge.api.model.SessionEntity;
import capgemini.challenge.api.repository.ISessionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class SessionServiceTest {


    @Mock
    private ISessionRepository sessionRepository;
    @Mock
    private MapStructMapper mapper;
    @Mock
    private UserStoryService userStoryService;
    @Mock
    private UserService userService;
    @InjectMocks
    private SessionService sessionService;

    @Test
    void getSessionById_Exists_ReturnsSession(){
        Mockito.when(this.sessionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new SessionEntity()));
        Mockito.when(this.mapper.sessionEntityToSession(Mockito.any(SessionEntity.class))).thenReturn(new Session());

        final var response = this.sessionService.getSessionById(1L);

        Assertions.assertNotNull(response);
    }

    @Test
    void getSessionById_NotExists_ThrowsException(){
        Mockito.when(this.sessionRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(PlanningPokerException.class, () -> this.sessionService.getSessionById(1L));
    }

    @Test
    void deleteSessionById_Exists_DeletesSession(){
        this.sessionService.deleteSessionById(1L);
        Mockito.verify(this.sessionRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void updateSession_Exists_ReturnsSession(){
        Session sessionMock = new Session();

        Mockito.when(this.sessionRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(this.sessionRepository.save(Mockito.any(SessionEntity.class))).thenReturn(new SessionEntity());
        Mockito.when(this.mapper.sessionToSessionEntity(Mockito.any(Session.class))).thenReturn(new SessionEntity());
        Mockito.when(this.mapper.sessionEntityToSession(Mockito.any(SessionEntity.class))).thenReturn(new Session());

        final var response = this.sessionService.updateSession(1L, sessionMock);

        Assertions.assertNotNull(response);
    }

    @Test
    void updateSession_NotExists_ThrowsException(){
        Session sessionMock = new Session();

        Mockito.when(this.sessionRepository.existsById(Mockito.anyLong())).thenReturn(false);

        Assertions.assertThrows(PlanningPokerException.class, () -> this.sessionService.updateSession(1L, sessionMock));
    }

    @Test
    void addSession_SessionSaved_ReturnsSession(){
        Session sessionMock = new Session();

        Mockito.when(this.mapper.sessionToSessionEntity(Mockito.any(Session.class))).thenReturn(new SessionEntity());
        Mockito.when(this.mapper.sessionEntityToSession(Mockito.any(SessionEntity.class))).thenReturn(new Session());
        Mockito.when(this.sessionRepository.save(Mockito.any(SessionEntity.class))).thenReturn(new SessionEntity());

        final var response = this.sessionService.addSession(sessionMock);

        Assertions.assertNotNull(response);
    }

    @Test
    void deleteUserStorySession_SessionAndUSExists_ReturnsSession(){
        Session sessionMock = new Session();
        UserStory userStory = new UserStory();
        userStory.setUserStoryId(1L);
        sessionMock.setUserStories(List.of(userStory));
        SessionService spyTemp = Mockito.spy(sessionService);

        Mockito.doReturn(sessionMock).when(spyTemp).getSessionById(Mockito.anyLong());
        Mockito.when(this.sessionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new SessionEntity()));
        Mockito.when(this.userStoryService.getUserStoryById(Mockito.anyLong())).thenReturn(userStory);
        Mockito.when(this.mapper.sessionToSessionEntity(Mockito.any(Session.class))).thenReturn(new SessionEntity());
        Mockito.when(this.mapper.sessionEntityToSession(Mockito.any(SessionEntity.class))).thenReturn(new Session());
        Mockito.when(this.sessionRepository.save(Mockito.any(SessionEntity.class))).thenReturn(new SessionEntity());

        final var response = spyTemp.deleteUserStorySession(1L, 1L);

        Assertions.assertNotNull(response);
    }

    @Test
    void deleteUserStorySession_USNotExists_ThrowsException(){
        Session sessionMock = new Session();
        UserStory userStory = new UserStory();
        userStory.setUserStoryId(2L);
        sessionMock.setUserStories(List.of(userStory));
        SessionService spyTemp = Mockito.spy(sessionService);

        Mockito.when(this.sessionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new SessionEntity()));
        Mockito.doReturn(sessionMock).when(spyTemp).getSessionById(Mockito.anyLong());

        Assertions.assertThrows(PlanningPokerException.class, () -> spyTemp.deleteUserStorySession(1L, 1L));
    }

    @Test
    void uploadSessionUserStory_USNotExists_ThrowsException(){
        Session sessionMock = new Session();
        SessionService spyTemp = Mockito.spy(sessionService);

        Mockito.doReturn(sessionMock).when(spyTemp).getSessionById(Mockito.anyLong());
        Mockito.when(this.userStoryService.getUserStoryById(Mockito.anyLong())).thenReturn(null);

        Assertions.assertThrows(PlanningPokerException.class, () -> spyTemp.uploadSessionUserStory(1L, 1L));
    }

    @Test
    void uploadSessionUserStory_USAlreadyAtSession_ThrowsException(){
        Session sessionMock = new Session();
        UserStory userStory = new UserStory().userStoryId(1L);
        sessionMock.setUserStories(List.of(userStory));
        SessionService spyTemp = Mockito.spy(sessionService);

        Mockito.doReturn(sessionMock).when(spyTemp).getSessionById(Mockito.anyLong());
        Mockito.when(this.userStoryService.getUserStoryById(Mockito.anyLong())).thenReturn(userStory);

        Assertions.assertThrows(PlanningPokerException.class, () -> spyTemp.uploadSessionUserStory(1L, 1L));
    }

    @Test
    void uploadSessionUserStory_SessionAndUSExists_ReturnsSession(){
        Session sessionMock = new Session();
        UserStory userStory = new UserStory().userStoryId(2L);
        UserStory userStoryMock = new UserStory().userStoryId(1L);
        sessionMock.setUserStories(List.of(userStory));
        SessionService spyTemp = Mockito.spy(sessionService);

        Mockito.doReturn(sessionMock).when(spyTemp).getSessionById(Mockito.anyLong());
        Mockito.when(this.userStoryService.getUserStoryById(Mockito.anyLong())).thenReturn(userStoryMock);
        Mockito.when(this.mapper.sessionToSessionEntity(Mockito.any(Session.class))).thenReturn(new SessionEntity());
        Mockito.when(this.mapper.sessionEntityToSession(Mockito.any(SessionEntity.class))).thenReturn(new Session());
        Mockito.when(this.sessionRepository.save(Mockito.any(SessionEntity.class))).thenReturn(new SessionEntity());

        final var response = spyTemp.uploadSessionUserStory(1L, 1L);

        Assertions.assertNotNull(response);
    }

    @Test
    void uploadSessionUser_UserExistsAtSession_ThrowsException(){
        Session sessionMock = new Session();
        User user = new User()
                .userId(1L)
                .name("Test");
        sessionMock.setPlayers(List.of(user));
        SessionService spyTemp = Mockito.spy(sessionService);

        Mockito.doReturn(sessionMock).when(spyTemp).getSessionById(Mockito.anyLong());

        Assertions.assertThrows(PlanningPokerException.class, () -> this.sessionService.uploadSessionUser(1L, user));
    }

    @Test
    void uploadSessionUser_UserNotExists_CreateUserAndReturnsSession(){
        Session sessionMock = new Session();
        User user = new User()
                .userId(1L)
                .name("Test");
        sessionMock.setPlayers(List.of(new User()
                        .userId(2L)
                .name("Test1")));
        SessionService spyTemp = Mockito.spy(sessionService);

        Mockito.doReturn(sessionMock).when(spyTemp).getSessionById(Mockito.anyLong());
        Mockito.when(this.userService.getUserById(Mockito.anyLong())).thenReturn(null);
        Mockito.when(this.userService.createUser(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.mapper.sessionToSessionEntity(Mockito.any(Session.class))).thenReturn(new SessionEntity());
        Mockito.when(this.mapper.sessionEntityToSession(Mockito.any(SessionEntity.class))).thenReturn(new Session());
        Mockito.when(this.sessionRepository.save(Mockito.any(SessionEntity.class))).thenReturn(new SessionEntity());

        final var response = spyTemp.uploadSessionUser(1L, user);

        Assertions.assertNotNull(response);
    }

    @Test
    void uploadSessionUser_SessionAndUserExists_ReturnsSession(){
        Session sessionMock = new Session();
        User user = new User()
                .userId(1L)
                .name("Test");
        sessionMock.setPlayers(List.of());
        SessionService spyTemp = Mockito.spy(sessionService);

        Mockito.doReturn(sessionMock).when(spyTemp).getSessionById(Mockito.anyLong());
        Mockito.when(this.userService.getUserById(Mockito.anyLong())).thenReturn(user);
        Mockito.when(this.mapper.sessionToSessionEntity(Mockito.any(Session.class))).thenReturn(new SessionEntity());
        Mockito.when(this.mapper.sessionEntityToSession(Mockito.any(SessionEntity.class))).thenReturn(new Session());
        Mockito.when(this.sessionRepository.save(Mockito.any(SessionEntity.class))).thenReturn(new SessionEntity());

        final var response = spyTemp.uploadSessionUser(1L, user);

        Assertions.assertNotNull(response);
    }
}