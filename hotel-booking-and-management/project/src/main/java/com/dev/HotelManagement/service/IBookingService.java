package com.dev.HotelManagement.service;

import com.dev.HotelManagement.dto.BookingDTO;
import com.dev.HotelManagement.dto.ResponseDTO;

public interface IBookingService {

    ResponseDTO saveBooking(String roomId, String userId, BookingDTO bookingRequest);
    ResponseDTO findBookingByBookingId(String bookingId);
    ResponseDTO cancelBooking(String bookingId);
    ResponseDTO updateBooking(String bookingId, BookingDTO bookingRequest);
}
