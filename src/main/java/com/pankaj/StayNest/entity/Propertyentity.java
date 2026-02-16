package com.pankaj.StayNest.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "propertyentity", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

//    host_id (FK → User)
    @JoinColumn(name="host_id")
    @ManyToOne
    private Host host;

    @OneToMany(mappedBy ="propertyentity", cascade = CascadeType.ALL)
    private List<PropertyImage> images = new ArrayList<>();
}
