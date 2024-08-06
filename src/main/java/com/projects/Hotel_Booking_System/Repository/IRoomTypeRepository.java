package com.projects.Hotel_Booking_System.Repository;

import com.projects.Hotel_Booking_System.Model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomTypeRepository extends JpaRepository<RoomType, Integer> {

    RoomType findByCode(String code);
}
