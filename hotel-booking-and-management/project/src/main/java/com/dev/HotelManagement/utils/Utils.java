package com.dev.HotelManagement.utils;

import com.dev.HotelManagement.dto.BookingDTO;
import com.dev.HotelManagement.dto.RoomDTO;
import com.dev.HotelManagement.dto.UserDTO;
import com.dev.HotelManagement.entity.Booking;
import com.dev.HotelManagement.entity.Room;
import com.dev.HotelManagement.entity.User;


public class Utils {


    public static UserDTO mapUserEntityToUserDTO(User user) {
        // Fields mapping
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }

    public static RoomDTO mapRoomEntityToRoomDTO(Room room) {
        // Fields mapping
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getRoomId());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setRoomDescription(room.getRoomDescription());
        return roomDTO;
    }

    public static BookingDTO mapBookingEntityToBookingDTO(Booking booking) {
        // Fields mapping
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setTotalNumOfGuest(booking.getTotalNumOfGuest());
        bookingDTO.setUser(mapUserEntityToUserDTO(booking.getUser()));
        bookingDTO.setRoom(mapRoomEntityToRoomDTO(booking.getRoom()));
        return bookingDTO;
    }

    public static Booking mapBookingDTOToBookingEntity(BookingDTO bookingDTO) {
        // Fields mapping
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setCheckInDate(bookingDTO.getCheckInDate());
        booking.setCheckOutDate(bookingDTO.getCheckOutDate());
        booking.setTotalNumOfGuest(bookingDTO.getTotalNumOfGuest());
        booking.setBookingCost(bookingDTO.getBookingCost());
        return booking;
    }

    public static void updateBookingDTOToBookingEntity(Booking booking, Room room, BookingDTO bookingDTO) {
        // Fields mapping
        booking.setCheckInDate(bookingDTO.getCheckInDate());
        booking.setCheckOutDate(bookingDTO.getCheckOutDate());
        booking.setTotalNumOfGuest(bookingDTO.getTotalNumOfGuest());
        booking.setBookingCost(bookingDTO.getBookingCost());
        booking.setRoom(room);
    }

}


