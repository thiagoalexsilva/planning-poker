package capgemini.challenge.api.service.interfaces;

import capgemini.api.openapi.dto.Session;
import capgemini.api.openapi.dto.User;

public interface ISessionService {
    Session getSessionById(Long sessionId);

    void deleteSessionById(Long sessionId);

    Session updateSession(Long sessionId, Session session);

    Session addSession(Session session);

    Session deleteUserStorySession(Long sessionId, Long userStoryId);

    Session uploadSessionUserStory(Long sessionId, Long userStoryId);

    Session uploadSessionUser(Long sessionId, User user);
}
