package com.dev.HotelManagement.controller;


import com.dev.HotelManagement.dto.BookingDTO;
import com.dev.HotelManagement.dto.ResponseDTO;
import com.dev.HotelManagement.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")

public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @PostMapping("/booking/{roomId}/{userId}")
    public ResponseEntity<ResponseDTO> saveBookings(@PathVariable String roomId,
                                                    @PathVariable String userId,
                                                    @RequestBody BookingDTO bookingRequest) {
        ResponseDTO responseDTO = bookingService.saveBooking(roomId, userId, bookingRequest);
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
    }

    @GetMapping("/get/{bookingId}")
    public ResponseEntity<ResponseDTO> getBookingByBookingId(@PathVariable String bookingId) {
        ResponseDTO responseDTO = bookingService.findBookingByBookingId(bookingId);
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
    }

    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<ResponseDTO> cancelBooking(@PathVariable String bookingId) {
        ResponseDTO responseDTO = bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
    }

    @PutMapping("/update/{bookingId}")
    public ResponseEntity<ResponseDTO> updateBooking(@PathVariable String bookingId,
                                                     @RequestBody BookingDTO bookingRequest) {
        ResponseDTO responseDTO = bookingService.updateBooking(bookingId, bookingRequest);
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
    }
}
