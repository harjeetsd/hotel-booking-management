package com.dev.HotelManagement.repo;

import com.dev.HotelManagement.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, String> {
    Optional<Booking> findById(String bookingId);
}
