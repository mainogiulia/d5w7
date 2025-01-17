package it.epicode.d5w7.event;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventRequest {
    @NotNull(message = "Event title cannot be empty")
    private String titolo;
    private String description;
    @FutureOrPresent(message = "Booking date cannot be empty or a past date")
    private LocalDate date;
    @NotNull(message = "Event location cannot be empty")
    private String location;
    @Min(value = 1, message = "Event available places cannot be lower than 1")
    private int availablePlaces;
    @Min(value = 1, message = "Event Planner id cannot be lower than 1")
    private Long eventPlannerId;
}
