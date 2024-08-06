package com.projects.Hotel_Booking_System.Model.Response;

import com.projects.Hotel_Booking_System.Model.Enums.BookingStatus;
import com.projects.Hotel_Booking_System.Model.Guest;
import com.projects.Hotel_Booking_System.Model.Payment;
import com.projects.Hotel_Booking_System.Model.Room;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
@Builder
public class BookingResponse {

    private Date checkInDate;
    private Date checkOutDate;
    private Time checkOutTime;

    private Date reservationDate;

    private Double totalPrice;

    private BookingStatus status;
    private Room room;

    private Guest guest;

    private Payment payment;
}
