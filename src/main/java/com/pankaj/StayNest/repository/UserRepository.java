package com.pankaj.StayNest.repository;

import com.pankaj.StayNest.entity.User;
import com.pankaj.StayNest.entity.type.Authprovidertype;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernam(String username);

    Optional<User> findByProviderIdAndProviderType(String providerId, Authprovidertype providerType);
}