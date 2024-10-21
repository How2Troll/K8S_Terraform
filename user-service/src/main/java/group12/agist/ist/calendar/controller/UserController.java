package group12.agist.ist.calendar.controller;

import group12.agist.ist.calendar.entity.IO.CreateUserRequest;
import group12.agist.ist.calendar.entity.User;
import group12.agist.ist.calendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/user")
  public User createUser(@RequestBody CreateUserRequest request) {
    System.out.println("POST request received at /user with payload: " + request);
    return userService.createUser(request);
  }

  @GetMapping("/user/{userId}")
  public User getUser(@PathVariable Long userId) {
    System.out.println("GET request received at /user/" + userId);
    return userService.getUser(userId);
  }

  @GetMapping("/user")
  public List<User> getUsers(@RequestParam List<Long> userIds) {
    System.out.println("GET request received at /user, userIds: " + userIds);
    return userService.getUsers(userIds);
  }

}