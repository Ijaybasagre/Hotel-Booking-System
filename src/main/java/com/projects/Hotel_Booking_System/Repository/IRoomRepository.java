package com.projects.Hotel_Booking_System.Repository;

import com.projects.Hotel_Booking_System.Model.Enums.RoomStatus;
import com.projects.Hotel_Booking_System.Model.Room;
import com.projects.Hotel_Booking_System.Model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoomRepository extends JpaRepository<Room, Integer> {

    Room findByRoomNumber(int roomNumber);

    Room findFirstByRoomTypeAndStatus(RoomType roomType, RoomStatus status);

    List<Room> findByRoomTypeAndStatus(RoomType roomType, RoomStatus status);

    List<Room> findByStatus(RoomStatus status);
}
