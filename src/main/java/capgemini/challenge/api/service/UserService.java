package capgemini.challenge.api.service;

import capgemini.api.openapi.dto.User;
import capgemini.challenge.api.exception.PlanningPokerException;
import capgemini.challenge.api.mapper.MapStructMapper;
import capgemini.challenge.api.model.UserEntity;
import capgemini.challenge.api.repository.IUserRepository;
import capgemini.challenge.api.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final MapStructMapper mapper;

    @Autowired
    public UserService(final IUserRepository userRepository,
                       final MapStructMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = this.userRepository.findById(id)
                .orElseThrow(PlanningPokerException::new);
        return this.mapper.userEntityToUser(userEntity);
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = this.mapper.userToUserEntity(user);
        return this.mapper.userEntityToUser(this.userRepository.save(userEntity));
    }
}
