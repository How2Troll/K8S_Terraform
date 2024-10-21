package group12.agist.ist.calendar.repository;

import group12.agist.ist.calendar.entity.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventParticipantRepository extends JpaRepository<EventParticipant, Long> {

  List<EventParticipant> findByUserId(Long userId);

  List<EventParticipant> findByEventId(Long eventId);
}
