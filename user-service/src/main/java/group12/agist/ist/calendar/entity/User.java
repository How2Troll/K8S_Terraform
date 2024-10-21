package group12.agist.ist.calendar.entity;

import group12.agist.ist.calendar.entity.IO.CreateUserRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  public User(CreateUserRequest request) {
    this.name = request.name();
    this.email = request.email();
  }
}
