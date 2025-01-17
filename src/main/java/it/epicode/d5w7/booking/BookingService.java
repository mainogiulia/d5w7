package it.epicode.d5w7.booking;

import it.epicode.d5w7.auth.AppUser;
import it.epicode.d5w7.auth.AppUserRepository;
import it.epicode.d5w7.event.Event;
import it.epicode.d5w7.event.EventRepo;
import it.epicode.d5w7.exceptions.BookingNotFoundException;
import it.epicode.d5w7.exceptions.SoldOutException;
import it.epicode.d5w7.exceptions.UnauthorizedOperationException;
import it.epicode.d5w7.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepo bookingRepo;
    private final EventRepo eventRepo;
    private final AppUserRepository appUserRepository;

    private AppUser getLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public Booking createBooking(BookingRequest bookingRequest) {
        Event event = eventRepo.findById(bookingRequest.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));
        if (event.getAvailablePlaces() < bookingRequest.getPlacesToBook()) {
            throw new SoldOutException("No more places available for this event");
        }
        Booking booking = new Booking();
        booking.setEvent(event);
        booking.setUser(getLoggedInUser());
        booking.setBookingDate(LocalDate.now());
        booking.setNumOfPlaces(bookingRequest.getPlacesToBook());

        event.setAvailablePlaces(event.getAvailablePlaces() - bookingRequest.getPlacesToBook());

        eventRepo.save(event);
        return bookingRepo.save(booking);
    }

    public void deleteBooking(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        AppUser loggedInUser = getLoggedInUser();

        if (!booking.getUser().getId().equals(loggedInUser.getId())) {
            throw new UnauthorizedOperationException("You are not authorized to delete this booking");
        }

        bookingRepo.delete(booking);
    }

    public List<Booking> getUserBookings() {
        AppUser loggedInUser = getLoggedInUser();
        return bookingRepo.findByUserId(loggedInUser.getId());
    }
}
