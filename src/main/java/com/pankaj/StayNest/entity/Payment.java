package com.pankaj.StayNest.entity;


import com.pankaj.StayNest.entity.type.PaymentStatusType;
import com.pankaj.StayNest.entity.type.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

//  amount
    private double amount;


// paymentStatus (SUCCESS / FAILED / REFUNDED)
@ElementCollection(fetch = FetchType.EAGER)
@Enumerated(EnumType.STRING)
Set<PaymentStatusType> role=new HashSet<>();


//    paymentMethod

//            transactionId
    private Long transactionId;
//
//    booking_id (FK)
    @JoinColumn(name = "booking_id",unique = true)
    @OneToOne
    private Booking booking;
}
