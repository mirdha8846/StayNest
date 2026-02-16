package com.pankaj.StayNest.repository;

import com.pankaj.StayNest.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}