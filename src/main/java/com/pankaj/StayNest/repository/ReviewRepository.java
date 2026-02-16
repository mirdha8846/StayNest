package com.pankaj.StayNest.repository;

import com.pankaj.StayNest.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
