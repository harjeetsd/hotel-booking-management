package com.dev.HotelManagement.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {

    private String id;
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;
    @NotBlank(message = "Password is required")
    private String password;
    private List<BookingDTO> bookings = new ArrayList<>();

}
