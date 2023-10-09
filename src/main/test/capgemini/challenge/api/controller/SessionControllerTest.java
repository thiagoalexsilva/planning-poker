package capgemini.challenge.api.controller;

import capgemini.api.openapi.dto.Session;
import capgemini.api.openapi.dto.User;
import capgemini.challenge.api.service.SessionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class SessionControllerTest {

    @Mock
    private SessionService sessionService;
    @InjectMocks
    private SessionController sessionController;

    @Test
    void getSessionById_SessionExists_ReturnsSession() {
        final var sessionMock = new Session().sessionId(1L);

        Mockito.when(this.sessionService.getSessionById(Mockito.anyLong())).thenReturn(sessionMock);

        final var response = this.sessionController.getSessionById(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void getSessionById_SessionNotExists_ReturnsNotFound() {
        Mockito.when(this.sessionService.getSessionById(Mockito.anyLong())).thenReturn(null);

        final var response = this.sessionController.getSessionById(1L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteSession_SessionExists_ReturnsOK() {
        final var sessionMock = new Session().sessionId(1L);

        Mockito.when(this.sessionService.getSessionById(Mockito.anyLong())).thenReturn(sessionMock);
        Mockito.doNothing().when(this.sessionService).deleteSessionById(1L);

        final var response = this.sessionController.deleteSession(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteSession_SessionNotExists_ReturnsNotFound() {
        Mockito.when(this.sessionService.getSessionById(Mockito.anyLong())).thenReturn(null);

        final var response = this.sessionController.deleteSession(1L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateSession_SessionNotExists_ReturnsNotFound() {
        final var sessionMock = new Session().sessionId(1L);
        Mockito.when(this.sessionService.getSessionById(Mockito.anyLong())).thenReturn(null);

        final var response = this.sessionController.updateSession(1L, sessionMock);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    void updateSession_SessionExists_ReturnsOK() {
        final var sessionMock = new Session().sessionId(1L);

        Mockito.when(this.sessionService.getSessionById(Mockito.anyLong())).thenReturn(sessionMock);
        Mockito.when(this.sessionService.updateSession(Mockito.anyLong(), Mockito.any(Session.class))).thenReturn(sessionMock);

        final var response = this.sessionController.updateSession(1L, sessionMock);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void addSession() {
        final var sessionMock = new Session().sessionId(1L);

        Mockito.when(this.sessionService.addSession(Mockito.any(Session.class))).thenReturn(sessionMock);

        final var response = this.sessionController.addSession(sessionMock);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void uploadSessionUserStory_SessionNotExists_ReturnsNotFound() {
        Mockito.when(this.sessionService.getSessionById(Mockito.anyLong())).thenReturn(null);

        final var response = this.sessionController.uploadSessionUserStory(1L, 1L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void uploadSessionUserStory_SessionExists_ReturnsOK() {
        final var sessionMock = new Session().sessionId(1L);

        Mockito.when(this.sessionService.getSessionById(Mockito.anyLong())).thenReturn(sessionMock);
        Mockito.when(this.sessionService.uploadSessionUserStory(Mockito.anyLong(), Mockito.anyLong())).thenReturn(sessionMock);

        final var response = this.sessionController.uploadSessionUserStory(1L, 1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void uploadSessionUser_SessionNotExists_ReturnsNotFound() {
        final var userMock = new User().userId(1L);

        Mockito.when(this.sessionService.getSessionById(Mockito.anyLong())).thenReturn(null);

        final var response = this.sessionController.uploadSessionUser(1L, userMock);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void uploadSessionUse_SessionExists_ReturnsOK() {
        final var sessionMock = new Session().sessionId(1L);
        final var userMock = new User().userId(1L);

        Mockito.when(this.sessionService.getSessionById(Mockito.anyLong())).thenReturn(sessionMock);
        Mockito.when(this.sessionService.uploadSessionUser(Mockito.anyLong(), Mockito.any(User.class))).thenReturn(sessionMock);

        final var response = this.sessionController.uploadSessionUser(1L, userMock);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void deleteUserStorySession_SessionNotExists_ReturnsNotFound() {
        Mockito.when(this.sessionService.getSessionById(Mockito.anyLong())).thenReturn(null);

        final var response = this.sessionController.deleteUserStorySession(1L, 1L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteUserStorySession_SessionExists_ReturnsOK() {
        final var sessionMock = new Session().sessionId(1L);

        Mockito.when(this.sessionService.getSessionById(Mockito.anyLong())).thenReturn(sessionMock);
        Mockito.when(this.sessionService.deleteUserStorySession(Mockito.anyLong(), Mockito.anyLong())).thenReturn(sessionMock);

        final var response = this.sessionController.deleteUserStorySession(1L, 1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}