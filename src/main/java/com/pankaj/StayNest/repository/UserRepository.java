package com.pankaj.StayNest.repository;

import com.pankaj.StayNest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}