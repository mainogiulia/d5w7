package it.epicode.d5w7.booking;

import it.epicode.d5w7.auth.AppUser;
import it.epicode.d5w7.event.Event;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDate bookingDate;
    private int numOfPlaces;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}