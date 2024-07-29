package com.projects.Hotel_Booking_System.Repository;

import com.projects.Hotel_Booking_System.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Integer>{
}
