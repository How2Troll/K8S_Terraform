package group12.agist.ist.calendar.controller;

import group12.agist.ist.calendar.entity.Event;
import group12.agist.ist.calendar.entity.EventParticipant;
import group12.agist.ist.calendar.entity.IO.AddParticipantsRequest;
import group12.agist.ist.calendar.entity.IO.CreateEventRequest;
import group12.agist.ist.calendar.entity.UserDTO;
import group12.agist.ist.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CalendarController {

  @Autowired
  private EventService eventService;

  @PostMapping("/calendar")
  public Event createEvent(@RequestBody CreateEventRequest request) {
    return eventService.createEvent(request);
  }

  @PostMapping("/calendar/{eventId}/participants")
  public List<EventParticipant> addParticipants(@PathVariable Long eventId, @RequestBody AddParticipantsRequest request) {
    return eventService.addParticipants(eventId, request);
  }

  @GetMapping("/calendar/{userId}")
  public List<Event> getEvents(@PathVariable Long userId) {
    return eventService.getEvents(userId);
  }

  @GetMapping("/calendar/{eventId}/participants")
  public List<UserDTO> getParticipants(@PathVariable Long eventId) {
    return eventService.getParticipants(eventId);
  }
}