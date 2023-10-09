package capgemini.challenge.api.service.interfaces;

import capgemini.api.openapi.dto.User;

public interface IUserService {
    User getUserById(Long id);

    User createUser(User user);
}
