package com.pankaj.StayNest.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Propertyentity {

//    id (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String description;
    private Double pricePerNight;
    private String address;
    private String city;
    private String country;
    private Integer maxGuests;
    private LocalDate createdAt;
    private LocalDate updatedAt;

//    host_id (FK → User)
}
