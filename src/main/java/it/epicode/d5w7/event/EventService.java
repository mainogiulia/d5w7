package it.epicode.d5w7.event;

import it.epicode.d5w7.auth.AppUser;
import it.epicode.d5w7.auth.AppUserRepository;
import it.epicode.d5w7.exceptions.EventNotFoundException;
import it.epicode.d5w7.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepo eventRepo;
    private final AppUserRepository appUserRepository;

    public Event createEvent(EventRequest eventRequest) {
        AppUser eventPlanner = appUserRepository.findById(eventRequest.getEventPlannerId())
                .orElseThrow(() -> new UserNotFoundException("Event Planner not found"));
        Event event = new Event();
        BeanUtils.copyProperties(eventRequest, event);
        event.setEventPlanner(eventPlanner);
        return eventRepo.save(event);
    }

    public Event updateEvent(Long id, EventRequest eventRequest) {
        Event event = eventRepo.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));
        AppUser eventPlanner = appUserRepository.findById(eventRequest.getEventPlannerId())
                .orElseThrow(() -> new UserNotFoundException("Event Planner not found"));
        BeanUtils.copyProperties(eventRequest, event);
        event.setEventPlanner(eventPlanner);
        return eventRepo.save(event);
    }

    public void deleteEvent(Long id) {
        Event event = eventRepo.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));
        eventRepo.delete(event);
    }
}
