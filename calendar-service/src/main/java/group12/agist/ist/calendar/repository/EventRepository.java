package group12.agist.ist.calendar.repository;

import group12.agist.ist.calendar.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

  // return a List of Events from a List of Event Ids
  List<Event> findByIdIn(List<Long> eventIds);
}
