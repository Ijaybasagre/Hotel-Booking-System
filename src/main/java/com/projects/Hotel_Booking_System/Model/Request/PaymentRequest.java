package com.projects.Hotel_Booking_System.Model.Request;

import com.projects.Hotel_Booking_System.Model.Enums.PaymentMethod;
import com.projects.Hotel_Booking_System.Model.Enums.PaymentStatus;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentRequest {

    private Date paymentDate;
    private Double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private int bookingId;
}
