package group12.agist.ist.calendar.service;

import group12.agist.ist.calendar.entity.IO.CreateUserRequest;
import group12.agist.ist.calendar.entity.User;
import group12.agist.ist.calendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User createUser(CreateUserRequest request) {
    userRepository.findByEmail(request.email()).ifPresent(user -> {
      throw new RuntimeException("User already exists: " + user.getEmail());
    });
    User user = new User(request);
    return userRepository.save(user);
  }

  public User getUser(Long userId) {
    return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found: " + userId));
  }

  public List<User> getUsers(List<Long> userIds) {
    return userRepository.findByIdIn(userIds);
  }
}
