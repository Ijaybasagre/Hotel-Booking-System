package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.Room;
import com.projects.Hotel_Booking_System.Repository.IRoomRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final IRoomRepository roomRepository;

    public RoomService(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roomRepository.findAll(pageable).getContent();
    }

    public Room getRoom(int id) {
        return findById(id);
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
        roomToBeUpdate.setRoomType(room.getRoomType());
        roomToBeUpdate.setHotel(room.getHotel());
        return roomRepository.save(room);
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
