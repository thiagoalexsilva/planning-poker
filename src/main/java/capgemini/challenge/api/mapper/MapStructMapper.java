package capgemini.challenge.api.mapper;

import capgemini.api.openapi.dto.User;
import capgemini.api.openapi.dto.DeckFormat;
import capgemini.api.openapi.dto.Session;
import capgemini.api.openapi.dto.UserStory;
import capgemini.challenge.api.model.DeckFormatEntity;
import capgemini.challenge.api.model.SessionEntity;
import capgemini.challenge.api.model.UserEntity;
import capgemini.challenge.api.model.UserStoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "nickname", source = "nickname")
    User userEntityToUser(UserEntity user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "nickname", source = "nickname")
    UserEntity userToUserEntity(User user);

    @Mapping(target = "sessionId", source = "sessionId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "deckFormatId", source = "deckFormatId")
    Session sessionEntityToSession(SessionEntity sessionEntity);

    @Mapping(target = "sessionId", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "deckFormatId", source = "deckFormatId")
    SessionEntity sessionToSessionEntity(Session session);

    @Mapping(target = "deckFormatId", source = "deckFormatId")
    @Mapping(target = "name", source = "name")
    DeckFormat deckFormatEntityToDeckFormat(DeckFormatEntity deckFormatEntity);

    @Mapping(target = "userStoryId", source = "userStoryId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "storyPoints", source = "storyPoints")
    UserStory userStoryEntityToUserStory(UserStoryEntity userStoryEntity);


    @Mapping(target = "userStoryId", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "storyPoints", source = "storyPoints")
    UserStoryEntity userStoryToUserStoryEntity(UserStory userStory);

}
