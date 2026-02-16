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
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 40)
    private String username;
    @Column(nullable = false)
    private Long payoutAccountNumber;

    @JoinColumn(unique = true)
    @OneToOne
    @MapsId
    private User user;

    @OneToMany(mappedBy = "host",cascade = CascadeType.ALL)
    private List<Propertyentity> propertyentity=new ArrayList<>();


}
