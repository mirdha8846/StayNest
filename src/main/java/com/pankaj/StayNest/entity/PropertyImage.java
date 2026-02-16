package com.pankaj.StayNest.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image_url;
    //property_id (FK)
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Propertyentity propertyentity;
}
