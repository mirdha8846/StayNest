package com.pankaj.StayNest.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Review {

//    id
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
//
//    rating (1–5)

//
//    comment
private String comment;
//
//            createdAt
private LocalDate createdAt;
//
//    user_id (FK)
//
//    property_id (FK)
}
