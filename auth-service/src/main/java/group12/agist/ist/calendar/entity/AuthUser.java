package group12.agist.ist.calendar.entity;

import group12.agist.ist.calendar.entity.IO.AuthUserRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "auth_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, columnDefinition = "password_hashed")
  private String passwordHashed;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false)),
  })
  private UserDTO user;

  @Enumerated(EnumType.STRING)
  private Role role;

  public enum Role {
    USER,
    ADMIN
  }

  public AuthUser(AuthUserRequest request) {
    this.username = request.username();
    var passwordEncoder = new BCryptPasswordEncoder();
    this.passwordHashed = passwordEncoder.encode(request.password());
    //TODO connect to User in user-service
    var userDto = new UserDTO();
    userDto.setId(1L);
    userDto.setName("testName");
    userDto.setEmail("testEmail");
    this.user = userDto;
//    this.user = request.user();
    this.role = Role.USER;
  }
}
