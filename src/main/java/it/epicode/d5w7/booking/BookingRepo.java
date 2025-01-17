package it.epicode.d5w7.booking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Long> {
}
