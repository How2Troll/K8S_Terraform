package group12.agist.ist.calendar.controller;

import group12.agist.ist.calendar.entity.AuthUser;
import group12.agist.ist.calendar.entity.IO.AuthUserRequest;
import group12.agist.ist.calendar.entity.IO.LoginAction;
import group12.agist.ist.calendar.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  private AuthUserService authUserService;

  @PostMapping("/auth")
  public AuthUser createAuthUser(@RequestBody AuthUserRequest request) {
    return authUserService.createUser(request);
  }

  @PostMapping("/auth/login")
  public LoginAction login(@RequestBody AuthUserRequest request) {
    try {
      return new LoginAction(true, authUserService.login(request).getUsername());
    } catch (RuntimeException e) {
      return new LoginAction(false);
    }
  }

}