package com.pankaj.StayNest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 40)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;


    @JoinColumn(unique = true,name = "user_id")
    @OneToOne
    @MapsId
    private User user;

    @OneToMany(mappedBy = "guest",cascade = CascadeType.ALL)
    private List<Booking> booking=new ArrayList<>();
}
