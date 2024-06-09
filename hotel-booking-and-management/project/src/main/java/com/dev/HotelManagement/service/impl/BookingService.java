package com.dev.HotelManagement.service.impl;

import com.dev.HotelManagement.dto.BookingDTO;
import com.dev.HotelManagement.dto.ResponseDTO;
import com.dev.HotelManagement.dto.RoomDTO;
import com.dev.HotelManagement.dto.UserDTO;
import com.dev.HotelManagement.entity.Booking;
import com.dev.HotelManagement.entity.Room;
import com.dev.HotelManagement.entity.User;
import com.dev.HotelManagement.exception.HotelManagementException;
import com.dev.HotelManagement.repo.BookingRepository;
import com.dev.HotelManagement.repo.RoomRepository;
import com.dev.HotelManagement.repo.UserRepository;
import com.dev.HotelManagement.service.IBookingService;
import com.dev.HotelManagement.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookingService implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseDTO saveBooking(String roomId, String userId, BookingDTO bookingDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (bookingDTO.getCheckOutDate().isBefore(bookingDTO.getCheckInDate())) {
                log.error("Check in date must be before check out date");
                throw new IllegalArgumentException("Check in date must be before check out date");
            }
            Room room = roomRepository.findById(roomId).orElseThrow(() -> new HotelManagementException("Room Not Found"));
            User user = userRepository.findById(userId).orElseThrow(() -> new HotelManagementException("User Not Found"));

            List<Booking> existingBookings = room.getBookings();
            //check if room is available or not
            if (!isRoomAvailable(bookingDTO, existingBookings)) {
                throw new HotelManagementException("Room not Available for selected date range");
            }
            RoomDTO roomDTO = Utils.mapRoomEntityToRoomDTO(room);
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);
            bookingDTO.setRoom(roomDTO);
            bookingDTO.setUser(userDTO);
            Booking booking = Utils.mapBookingDTOToBookingEntity(bookingDTO);
            booking.setRoom(room);
            booking.setUser(user);
            log.info("Saving booking for user id {} room id {}", userId, roomId);
            bookingRepository.save(booking);
            responseDTO.setStatusCode(201);
            responseDTO.setMessage("Successfully Booked");
        } catch (HotelManagementException e) {
            responseDTO.setStatusCode(404);
            responseDTO.setMessage(e.getMessage());
        } catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage("Error while Saving the booking: " + e.getMessage());
        }
        return responseDTO;
    }

     @Override
     public ResponseDTO findBookingByBookingId(String bookingId) {
         ResponseDTO responseDTO = new ResponseDTO();
         try {
            log.info("Fetching booking details with Booking id");
            Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new HotelManagementException("Booking Not Found"));
            BookingDTO bookingDTO = Utils.mapBookingEntityToBookingDTO(booking);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("Successfully Fetched");
            responseDTO.setBooking(bookingDTO);
        } catch (HotelManagementException e) {
            responseDTO.setStatusCode(404);
            responseDTO.setMessage(e.getMessage());
        } catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage("Error Finding a booking: " + e.getMessage());

        }
        return responseDTO;
     }
    @Override
    public ResponseDTO cancelBooking(String bookingId) {

        ResponseDTO responseDTO = new ResponseDTO();
        try {
            bookingRepository.findById(bookingId).orElseThrow(() -> new HotelManagementException("Booking Does Not Exist"));
            bookingRepository.deleteById(bookingId);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("successfully Cancelled");
        } catch (HotelManagementException e) {
            responseDTO.setStatusCode(404);
            responseDTO.setMessage(e.getMessage());
        } catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage("Error while Cancelling a booking: " + e.getMessage());
        }
        return responseDTO;
    }
    @Override
    public ResponseDTO updateBooking(String bookingId, BookingDTO bookingDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new HotelManagementException("Booking Does Not Exist"));
            Room room = null;
            if (bookingDTO.getCheckOutDate()!=null&&bookingDTO.getCheckInDate()!=null&&bookingDTO.getCheckOutDate().isBefore(bookingDTO.getCheckInDate())) {
                log.error("Check in date must be before check out date");
                throw new IllegalArgumentException("Check in date must be before check out date");
            }
            if(bookingDTO.getRoom()!=null){
                //if room id to be updated
                room = roomRepository.findById(bookingDTO.getRoom().getId()).orElseThrow(() -> new HotelManagementException("Room Not Found"));
                List<Booking> existingBookings = room.getBookings();
                //check if room is available or not
                if (!isRoomAvailable(bookingDTO, existingBookings)) {
                    throw new HotelManagementException("Room not Available for selected date range");
                }
            }
            Utils.updateBookingDTOToBookingEntity(booking, room, bookingDTO);
            bookingRepository.save(booking);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("successfully updated");

        } catch (HotelManagementException e) {
            responseDTO.setStatusCode(404);
            responseDTO.setMessage(e.getMessage());

        } catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage("Error Updating the booking: " + e.getMessage());

        }
        return responseDTO;
    }


    private boolean isRoomAvailable(BookingDTO bookingRequest, List<Booking> existingBookings) {
//if overlapping with existing bookings return false
        return existingBookings.stream()
                .noneMatch(existingBooking ->
                                (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                                        && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
        || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())
                && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))
                        ||
                        bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                                || bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate())
        );
    }
}
