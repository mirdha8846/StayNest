package com.pankaj.StayNest.repository;

import com.pankaj.StayNest.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}