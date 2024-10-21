package group12.agist.ist.calendar.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "event_participants")
@Data
@NoArgsConstructor
public class EventParticipant implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "event_id", nullable = false)
  private Long eventId;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  public EventParticipant(Long eventId, Long userId) {
    this.eventId = eventId;
    this.userId = userId;
  }
}