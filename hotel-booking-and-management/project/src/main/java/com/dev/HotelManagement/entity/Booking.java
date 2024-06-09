package com.dev.HotelManagement.entity;


import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;
    private double bookingCost;
    private int totalNumOfGuest;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
