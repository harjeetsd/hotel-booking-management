package com.dev.HotelManagement.dto;


import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class BookingDTO {
    private String id;
    @NotNull(message = "check in date is required")
    private LocalDate checkInDate;
    @Future(message = "check out date must be in the future")
    private LocalDate checkOutDate;
    private int totalNumOfGuest;
    private double bookingCost;
    private UserDTO user;
    private RoomDTO room;
}
