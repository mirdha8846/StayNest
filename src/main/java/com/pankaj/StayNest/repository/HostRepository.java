package com.pankaj.StayNest.repository;

import com.pankaj.StayNest.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {
}