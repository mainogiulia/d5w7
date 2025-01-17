package it.epicode.d5w7.event;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventRequest {
    private String titolo;
    private String description;
    private LocalDate date;
    private String location;
    private int availablePlaces;
    private Long eventPlannerId;
}
