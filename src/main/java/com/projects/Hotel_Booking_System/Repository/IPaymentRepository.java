package com.projects.Hotel_Booking_System.Repository;

import com.projects.Hotel_Booking_System.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
}
