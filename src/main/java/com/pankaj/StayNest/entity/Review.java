package com.pankaj.StayNest.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Review {

//    id
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

//    rating (1–5)


//    comment
private String comment;

private LocalDate createdAt;

//    user_id (FK)
@JoinColumn(name = "guest_id")
    @ManyToOne
    private Guest guest;

//    property_id (FK)
@JoinColumn(name = "property_id")
    @ManyToOne
    private Propertyentity propertyentity;
}
