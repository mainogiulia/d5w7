package it.epicode.d5w7.booking;

import it.epicode.d5w7.auth.AppUser;
import it.epicode.d5w7.event.Event;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class BookingRequest {
    @Min(value = 1, message = "Event id cannot be lower than 1")
    private Long eventId;
    private int availablePlaces;
}
