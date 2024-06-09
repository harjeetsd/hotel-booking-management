package com.dev.HotelManagement.service;

import com.dev.HotelManagement.dto.BookingDTO;
import com.dev.HotelManagement.dto.ResponseDTO;
import com.dev.HotelManagement.entity.Booking;
import com.dev.HotelManagement.entity.Room;
import com.dev.HotelManagement.entity.User;
import com.dev.HotelManagement.repo.BookingRepository;
import com.dev.HotelManagement.repo.RoomRepository;
import com.dev.HotelManagement.repo.UserRepository;
import com.dev.HotelManagement.service.impl.BookingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookingTests {
    @Mock
    BookingRepository bookingRepository;
    @Mock
    RoomRepository roomRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    BookingService bookingService;

    @BeforeEach
    void setUp() {
        Booking booking = new Booking();
        User user = new User();
        user.setId("123");
        user.setName("harjeet");
        user.setPassword("12323");
        user.setPhoneNumber("8990909409");
        Room room = new Room();
        room.setRoomDescription("Luxury Room");
        room.setRoomId("1212");
        room.setRoomType("Business");
        room.setRoomPrice(BigDecimal.valueOf(1121));
        Mockito.when(roomRepository.findById(Mockito.any())).thenReturn(Optional.of(room));
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
    }

    @Test
    void saveBooking() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCheckInDate(LocalDate.now().plusDays(1));
        bookingDTO.setCheckOutDate(LocalDate.now().plusDays(2));
        bookingDTO.setTotalNumOfGuest(3);
        ResponseDTO responseDTO = bookingService.saveBooking("1212", "123", bookingDTO);
        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(responseDTO.getStatusCode(), 201);
        Assertions.assertEquals(responseDTO.getMessage(), "Successfully Booked");
    }
}
