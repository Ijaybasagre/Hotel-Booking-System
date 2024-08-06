package com.projects.Hotel_Booking_System.Repository;

import com.projects.Hotel_Booking_System.Model.Booking;
import com.projects.Hotel_Booking_System.Model.Enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Integer> {


    List<Booking> findAllByStatus(BookingStatus status);

    List<Booking> findByCheckInDate(LocalDate checkInDate);
}
