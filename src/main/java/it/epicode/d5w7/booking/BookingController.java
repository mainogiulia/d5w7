package it.epicode.d5w7.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest) {
        return new ResponseEntity<>(bookingService.createBooking(bookingRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
