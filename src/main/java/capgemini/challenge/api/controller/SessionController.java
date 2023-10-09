package capgemini.challenge.api.controller;


import capgemini.api.openapi.api.SessionApi;
import capgemini.api.openapi.dto.Session;
import capgemini.api.openapi.dto.User;
import capgemini.challenge.api.service.interfaces.ISessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class SessionController implements SessionApi {

    private final ISessionService sessionService;

    public SessionController(final ISessionService sessionService){
        this.sessionService = sessionService;
    }

    @Override
    public ResponseEntity<Session> getSessionById(Long sessionId) {
        Session session = this.sessionService.getSessionById(sessionId);

        if(Objects.isNull(session)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(session);
    }

    @Override
    public ResponseEntity<Void> deleteSession(Long sessionId) {
        Session session = this.sessionService.getSessionById(sessionId);

        if(Objects.isNull(session)){
            return ResponseEntity.notFound().build();
        }

        this.sessionService.deleteSessionById(sessionId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Session> updateSession(Long sessionId, Session session) {
        Session sessionRepo = this.sessionService.getSessionById(sessionId);

        if(Objects.isNull(sessionRepo)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(this.sessionService.updateSession(sessionId, session));
    }

    @Override
    public ResponseEntity<Session> addSession(Session session) {
        return ResponseEntity.ok(this.sessionService.addSession(session));
    }

    @Override
    public ResponseEntity<Session> uploadSessionUserStory(Long sessionId, Long userStoryId) {
        Session session = this.sessionService.getSessionById(sessionId);

        if(Objects.isNull(session)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.sessionService.uploadSessionUserStory(sessionId, userStoryId));
    }

    @Override
    public ResponseEntity<Session> uploadSessionUser(Long sessionId, User user) {
        Session session = this.sessionService.getSessionById(sessionId);

        if(Objects.isNull(session)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.sessionService.uploadSessionUser(sessionId, user));
    }

    @Override
    public ResponseEntity<Void> deleteUserStorySession(Long sessionId, Long userStoryId) {
        Session session = this.sessionService.getSessionById(sessionId);

        if(Objects.isNull(session)){
            return ResponseEntity.notFound().build();
        }
        this.sessionService.deleteUserStorySession(sessionId, userStoryId);
        return ResponseEntity.ok().build();
    }
}
