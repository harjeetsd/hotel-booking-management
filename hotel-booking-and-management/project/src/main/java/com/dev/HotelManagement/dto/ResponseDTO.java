package com.dev.HotelManagement.dto;


import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO {

    private int statusCode;
    private String message;
    private UserDTO user;
    private RoomDTO room;
    private BookingDTO booking;
    private List<UserDTO> userList;
    private List<RoomDTO> roomList;
    private List<BookingDTO> bookingList;

}
