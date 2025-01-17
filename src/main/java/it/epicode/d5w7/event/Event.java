package it.epicode.d5w7.event;

import it.epicode.d5w7.auth.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titolo;
    private String description;
    private LocalDate date;
    private String location;

    @Column(name = "available_places")
    private int availablePlaces;

    @ManyToOne
    @JoinColumn(name = "eventPlanner_id")
    private AppUser eventPlanner;
}
