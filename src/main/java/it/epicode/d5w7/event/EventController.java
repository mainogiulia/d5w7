package it.epicode.d5w7.event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_EVENTPLANNER')")
    public ResponseEntity<Event> createEvent(@RequestBody EventRequest eventRequest){
        Event event = eventService.createEvent(eventRequest);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EVENTPLANNER')")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody EventRequest eventRequest) {
        Event event = eventService.updateEvent(id, eventRequest);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
}
