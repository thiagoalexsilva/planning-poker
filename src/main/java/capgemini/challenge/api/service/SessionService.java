package capgemini.challenge.api.service;

import capgemini.api.openapi.dto.Session;
import capgemini.api.openapi.dto.User;
import capgemini.api.openapi.dto.UserStory;
import capgemini.challenge.api.exception.PlanningPokerException;
import capgemini.challenge.api.mapper.MapStructMapper;
import capgemini.challenge.api.model.SessionEntity;
import capgemini.challenge.api.repository.ISessionRepository;
import capgemini.challenge.api.service.interfaces.ISessionService;
import capgemini.challenge.api.service.interfaces.IUserService;
import capgemini.challenge.api.service.interfaces.IUserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SessionService implements ISessionService {

    private final IUserStoryService userStoryService;
    private final IUserService userService;
    private final ISessionRepository sessionRepository;
    private final MapStructMapper mapper;

    @Autowired
    public SessionService (final IUserStoryService userStoryService,
                           final IUserService userService,
                           final ISessionRepository sessionRepository,
                           final MapStructMapper mapper){
        this.userStoryService = userStoryService;
        this.userService = userService;
        this.sessionRepository = sessionRepository;
        this.mapper = mapper;
    }

    @Override
    public Session getSessionById(Long sessionId) {
        SessionEntity sessionEntity = this.sessionRepository.findById(sessionId)
                .orElseThrow(PlanningPokerException::new);
        return this.mapper.sessionEntityToSession(sessionEntity);
    }

    @Override
    public void deleteSessionById(Long sessionId) {
        this.sessionRepository.deleteById(sessionId);
    }

    @Override
    public Session updateSession(Long sessionId, Session session) {
        if(!this.sessionRepository.existsById(sessionId)){
            throw new PlanningPokerException("Session does not exists.");
        }

        SessionEntity sessionEntity = this.mapper.sessionToSessionEntity(session);

        return this.mapper.sessionEntityToSession(this.sessionRepository.save(sessionEntity));
    }

    @Override
    public Session addSession(Session session) {
        SessionEntity sessionEntity = this.mapper.sessionToSessionEntity(session);
        return this.mapper.sessionEntityToSession(this.sessionRepository.save(sessionEntity));
    }

    @Override
    public Session deleteUserStorySession(Long sessionId, Long userStoryId) {
        Session session = this.getSessionById(sessionId);
        if(!this.validateUserStoryExistsAtSession(session, userStoryId)){
            throw new PlanningPokerException("user story not assigned to this session");
        }

        UserStory userStoryById = this.userStoryService.getUserStoryById(userStoryId);
        ArrayList<UserStory> userStoriesList = new ArrayList<>(session.getUserStories());
        userStoriesList.remove(userStoryById);

        session.setUserStories(userStoriesList);
        SessionEntity sessionEntity = this.mapper.sessionToSessionEntity(session);
        return this.mapper.sessionEntityToSession(this.sessionRepository.save(sessionEntity));
    }

    private boolean validateUserStoryExistsAtSession(Session session, Long userStoryId) {
        List<UserStory> userStories = session.getUserStories()
                .stream()
                .filter(userStory -> userStory.getUserStoryId().equals(userStoryId))
                .toList();

        return !userStories.isEmpty();
    }

    @Override
    public Session uploadSessionUserStory(Long sessionId, Long userStoryId) {
        Session session = this.getSessionById(sessionId);
        UserStory userStoryById = this.userStoryService.getUserStoryById(userStoryId);

        if(Objects.isNull(userStoryById)){
            throw new PlanningPokerException("user story not exists");
        } else if(this.validateUserStoryExistsAtSession(session, userStoryId)){
            throw new PlanningPokerException("user story already added to this session.");
        }

        ArrayList<UserStory> userStoriesList = new ArrayList<>(session.getUserStories());
        userStoriesList.add(userStoryById);

        session.setUserStories(userStoriesList);
        SessionEntity sessionEntity = this.mapper.sessionToSessionEntity(session);
        return this.mapper.sessionEntityToSession(this.sessionRepository.save(sessionEntity));
    }

    @Override
    public Session uploadSessionUser(Long sessionId, User user) {
        Session session = this.getSessionById(sessionId);
        String userName = user.getName();
        List<User> userPlayerList = session.getPlayers()
                .stream()
                .filter(player -> player.getName().equals(userName))
                .toList();

        if(!userPlayerList.isEmpty()){
            throw new PlanningPokerException("user story already added to this session.");
        } else if(Objects.isNull(this.userService.getUserById(user.getUserId()))){
            user = this.userService.createUser(user);
        }

        ArrayList<User> userList = new ArrayList<>(session.getPlayers());
        userList.add(user);
        session.setPlayers(userList);
        SessionEntity sessionEntity = this.mapper.sessionToSessionEntity(session);
        return this.mapper.sessionEntityToSession(this.sessionRepository.save(sessionEntity));
    }
}
