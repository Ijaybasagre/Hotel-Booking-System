package com.projects.Hotel_Booking_System.Model.Response;

import com.projects.Hotel_Booking_System.Model.Booking;
import com.projects.Hotel_Booking_System.Model.Enums.PaymentMethod;
import com.projects.Hotel_Booking_System.Model.Enums.PaymentStatus;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentResponse {

    private Date paymentDate;
    private Double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private Booking booking;
}
