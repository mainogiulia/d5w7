package it.epicode.d5w7.booking;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class BookingRequest {
    @Min(value = 1, message = "Event id cannot be lower than 1")
    private Long eventId;
    @Min(value = 1, message = "You cannot book less than 1 place")
    private int placesToBook;
}
