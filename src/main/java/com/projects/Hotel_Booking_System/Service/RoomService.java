package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.Enums.RoomStatus;
import com.projects.Hotel_Booking_System.Model.Room;
import com.projects.Hotel_Booking_System.Model.RoomType;
import com.projects.Hotel_Booking_System.Repository.IRoomRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final IRoomRepository roomRepository;

    private final RoomTypeService roomTypeService;
    
    public RoomService(IRoomRepository roomRepository, RoomTypeService roomTypeService) {
        this.roomRepository = roomRepository;
        this.roomTypeService = roomTypeService;
    }

    public List<Room> getAllRooms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roomRepository.findAll(pageable).getContent();
    }

    public Room getRoomByTypeAndStatus(String code, String status) {
        RoomType roomType = roomTypeService.getRoomTypeByCode(code);
        RoomStatus roomStatus = RoomStatus.getStatus(status);
        return roomRepository.findFirstByRoomTypeAndStatus(roomType, roomStatus);
    }

    public List<Room> getRoomsByTypeAndStatus(String code, String status) {
        RoomType roomType = roomTypeService.getRoomTypeByCode(code);
        RoomStatus roomStatus = RoomStatus.getStatus(status);
        return roomRepository.findByRoomTypeAndStatus(roomType, roomStatus);
    }

    public List<Room> getRoomByStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalStateException("Status cannot be empty");
        }
        return roomRepository.findByStatus(RoomStatus.getStatus(status));
    }

    public Room getRoom(int id) {
        return findById(id);
    }

    public Room getRoomByRoomNumber(int roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Room room, int id) {
        Room roomToBeUpdate = findById(id);
        roomToBeUpdate.setRoomNumber(room.getRoomNumber());
        roomToBeUpdate.setMaximumOccupancy(room.getMaximumOccupancy());
        roomToBeUpdate.setAvailability(room.getAvailability());
        roomToBeUpdate.setRating(room.getRating());
        roomToBeUpdate.setStatus(room.getStatus());
        roomToBeUpdate.setRoomType(room.getRoomType());
        roomToBeUpdate.setHotel(room.getHotel());
        return roomRepository.save(roomToBeUpdate);
    }


    public void updateRoom(Room room) {
        roomRepository.save(room);
    }

    public void deleteRoom(int id) {
        findById(id);
        roomRepository.deleteById(id);
    }

    protected Room findById(int id) {
        return roomRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Room with id " + id + " does not exist"));
    }
}
