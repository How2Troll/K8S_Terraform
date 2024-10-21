package group12.agist.ist.calendar.service;

import group12.agist.ist.calendar.entity.AuthUser;
import group12.agist.ist.calendar.entity.IO.AuthUserRequest;
import group12.agist.ist.calendar.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

  @Autowired
  private AuthUserRepository authUserRepository;

  public AuthUser createUser(AuthUserRequest request) {
    authUserRepository.findByUsername(request.username()).ifPresent(user -> {
      throw new RuntimeException("User already exists: " + user.getUsername());
    });
    AuthUser user = new AuthUser(request);
    return authUserRepository.save(user);
  }

  public AuthUser login(AuthUserRequest request) {
    var passwordEncoder = new BCryptPasswordEncoder();
    var user = authUserRepository.findByUsername(request.username())
        .orElseThrow(() -> new RuntimeException("Invalid username or password"));
    if (!passwordEncoder.matches(request.password(), user.getPasswordHashed())) {
      throw new RuntimeException("Invalid username or password");
    }
    return user;
  }
}
