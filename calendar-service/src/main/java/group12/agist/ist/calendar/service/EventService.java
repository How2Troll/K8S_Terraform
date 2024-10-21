package group12.agist.ist.calendar.service;

import group12.agist.ist.calendar.entity.Event;
import group12.agist.ist.calendar.entity.EventParticipant;
import group12.agist.ist.calendar.entity.IO.AddParticipantsRequest;
import group12.agist.ist.calendar.entity.IO.CreateEventRequest;
import group12.agist.ist.calendar.entity.UserDTO;
import group12.agist.ist.calendar.repository.EventParticipantRepository;
import group12.agist.ist.calendar.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private EventParticipantRepository eventParticipantRepository;

  @Autowired
  private RestTemplate restTemplate;


  @Value("${user-service.url}")
  private String userServiceUrl;

  public Event createEvent(CreateEventRequest request) {
    //TODO validate the user exists
    var event = new Event(request);
    var result = eventRepository.save(event);
    eventParticipantRepository.save(new EventParticipant(result.getId(), request.userId()));
    return result;
  }

  public List<EventParticipant> addParticipants(Long eventId, AddParticipantsRequest request) {
    //TODO validate the event exists
    //TODO validate the users exist
    var participants = request.userIds().stream()
        .map(userId -> new EventParticipant(eventId, userId))
        .toList();
    return eventParticipantRepository.saveAll(participants);
  }

  public List<Event> getEvents(Long userId) {
    var eventIds = eventParticipantRepository
        .findByUserId(userId).stream()
        .map(EventParticipant::getEventId).toList();
    return eventRepository.findByIdIn(eventIds);
  }

  public List<UserDTO> getParticipants(Long eventId) {
    var eventParticipants = eventParticipantRepository.findByEventId(eventId);
    System.out.println("Event participants: " + eventParticipants);
    String url = userServiceUrl + "/user?userIds=" + eventParticipants.stream().map(EventParticipant::getUserId).map(String::valueOf).collect(Collectors.joining(","));
    var response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<UserDTO>>() {
        }
    );
    System.out.println("Response: " + response);
    return response.getBody();
  }
}
