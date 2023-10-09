package capgemini.challenge.api.controller;

import capgemini.api.openapi.api.UserApi;
import capgemini.api.openapi.dto.User;
import capgemini.challenge.api.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

    private final IUserService userService;

    @Autowired
    public UserController(final IUserService userService){
        this.userService = userService;
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        return new ResponseEntity(this.userService.createUser(user), HttpStatus.CREATED);
    }
}
