package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.Employee;
import com.projects.Hotel_Booking_System.Model.Hotel;
import com.projects.Hotel_Booking_System.Model.Room;
import com.projects.Hotel_Booking_System.Repository.IHotelRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final IHotelRepository hotelRepository;

    private final RoomService roomService;

    private final EmployeeService employeeService;

    public HotelService(IHotelRepository hotelRepository, RoomService roomService, EmployeeService employeeService) {
        this.hotelRepository = hotelRepository;
        this.roomService = roomService;
        this.employeeService = employeeService;
    }


    public List<Hotel> getAllHotels(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return hotelRepository.findAll(pageable).getContent();
    }

    public Hotel getHotel(int id) {
        return findById(id);
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }


    public Hotel updateHotel(Hotel hotel, int hotelId) {
        Hotel hotelToBeUpdate = findById(hotelId);
        hotelToBeUpdate.setName(hotel.getName());
        hotelToBeUpdate.setAddress(hotel.getAddress());
        hotelToBeUpdate.setContactNumber(hotel.getContactNumber());
        hotelToBeUpdate.setEmail(hotel.getEmail());
        hotelToBeUpdate.setRating(hotel.getRating());
        hotelToBeUpdate.setRooms(hotel.getRooms());
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(int id) {
        hotelRepository.deleteById(id);
    }

    public Hotel addRoomToHotel(int hotelId, int roomId) {
        Hotel hotel = findById(hotelId);
        Room room = roomService.findById(roomId);
        room.setHotel(hotel);
        hotel.getRooms().add(room);
        return hotelRepository.save(hotel);
    }


    public Hotel addEmployeeToHotel(int hotelId, int employeeId) {
        Hotel hotel = findById(hotelId);
        Employee employee = employeeService.findById(employeeId);
        employee.setHotel(hotel);
        hotel.getEmployees().add(employee);
        return hotelRepository.save(hotel);
    }

    private Hotel findById(int hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(
                () -> new IllegalStateException("Hotel with id " + hotelId + " does not exist"));
    }
}
