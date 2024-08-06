package com.projects.Hotel_Booking_System.Model.Request;

import com.projects.Hotel_Booking_System.Model.Enums.BookingStatus;
import com.projects.Hotel_Booking_System.Model.Guest;
import com.projects.Hotel_Booking_System.Model.Payment;
import com.projects.Hotel_Booking_System.Model.RoomType;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class BookingRequest {

    private Date checkInDate;
    private Date checkOutDate;
    private Time checkOutTime;

    private Date reservationDate;

    private Double totalPrice;

    private BookingStatus status;
    private RoomType roomType;

    private Guest guest;

    private Payment payment;

}
