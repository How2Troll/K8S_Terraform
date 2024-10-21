package group12.agist.ist.calendar.entity;

import group12.agist.ist.calendar.entity.IO.CreateEventRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private LocalDateTime startTime;

  @Column(nullable = false)
  private LocalDateTime endTime;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false)),
  })
  private UserDTO user;

  public Event(CreateEventRequest request) {
    this.title = request.title();
    this.description = request.description();
    this.startTime = request.startTime();
    this.endTime = request.endTime();
    var user = new UserDTO();
    user.setId(request.userId());
    this.user = user;
  }
}
