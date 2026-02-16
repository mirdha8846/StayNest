package com.pankaj.StayNest.repository;

import com.pankaj.StayNest.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}