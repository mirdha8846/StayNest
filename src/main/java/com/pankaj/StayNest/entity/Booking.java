package com.pankaj.StayNest.entity;


import com.pankaj.StayNest.entity.type.StatusType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
//    id (PK)
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
//
//    checkInDate
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private  double totalPrice;

//    status (PENDING / CONFIRMED / CANCELLED)
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    Set<StatusType> staus=new HashSet<>();
//
//    guest_id (FK → User)
//
//    property_id (FK → Property)
//
    private LocalDate createdAt;
}
