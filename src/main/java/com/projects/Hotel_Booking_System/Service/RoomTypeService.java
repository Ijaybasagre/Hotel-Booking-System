package com.projects.Hotel_Booking_System.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projects.Hotel_Booking_System.Model.Rate;
import com.projects.Hotel_Booking_System.Model.Room;
import com.projects.Hotel_Booking_System.Model.RoomType;
import com.projects.Hotel_Booking_System.Repository.IRoomTypeRepository;
import jakarta.persistence.OneToMany;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    private IRoomTypeRepository roomTypeRepository;

    public RoomTypeService(IRoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomType> getAllRoomTypes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roomTypeRepository.findAll(pageable).getContent();
    }

    public RoomType getRoomType(int id) {
        return findById(id);
    }

    public RoomType addRoomType(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    public RoomType updateRoomType(RoomType roomType, int id) {
        RoomType roomTypeToBeUpdate = findById(id);
        roomTypeToBeUpdate.setName(roomType.getName());
        roomTypeToBeUpdate.setDescription(roomType.getDescription());
        roomTypeToBeUpdate.setAmenities(roomType.getAmenities());
        roomTypeToBeUpdate.setBaseRate(roomType.getBaseRate());
        roomTypeToBeUpdate.setRooms(roomType.getRooms());
        roomTypeToBeUpdate.setRates(roomType.getRates());
        return roomTypeRepository.save(roomType);
    }

    public void deleteRoomType(int id) {
        findById(id);
        roomTypeRepository.deleteById(id);
    }

    private RoomType findById(int id) {
        return roomTypeRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("RoomType with id " + id + " does not exist"));
    }



}
