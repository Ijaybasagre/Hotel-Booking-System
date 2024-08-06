package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.Employee;
import com.projects.Hotel_Booking_System.Model.Hotel;
import com.projects.Hotel_Booking_System.Model.Room;
import com.projects.Hotel_Booking_System.Repository.IHotelRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Hotel addHotel(Hotel hotel) {
        if (hotel.getRooms() != null) {
            hotel.getRooms().forEach(room -> room.setHotel(hotel));
        }
        if (hotel.getEmployees() != null) {
            hotel.getEmployees().forEach(employee -> employee.setHotel(hotel));
        }
        return hotelRepository.save(hotel);
    }


    @Transactional
    public Hotel updateHotel(Hotel hotel, int hotelId) {
        Hotel hotelToBeUpdate = findById(hotelId);
        hotelToBeUpdate.setName(hotel.getName());
        hotelToBeUpdate.setAddress(hotel.getAddress());
        hotelToBeUpdate.setContactNumber(hotel.getContactNumber());
        hotelToBeUpdate.setEmail(hotel.getEmail());
        hotelToBeUpdate.setRating(hotel.getRating());
        if (hotel.getRooms() != null) {
            hotel.getRooms().forEach(room -> room.setHotel(hotel));
        }
        hotelToBeUpdate.setRooms(hotel.getRooms());

        if (hotel.getEmployees() != null) {
            hotel.getEmployees().forEach(employee -> employee.setHotel(hotel));
        }

        hotelToBeUpdate.setEmployees(hotel.getEmployees());
        return hotelRepository.save(hotelToBeUpdate);
    }

    @Transactional
    public void deleteHotel(int id) {
        hotelRepository.deleteById(id);
    }

    @Transactional
    public Hotel addRoomToHotel(int hotelId, int roomId) {
        Hotel hotel = findById(hotelId);
        Room room = roomService.findById(roomId);
        room.setHotel(hotel);
        hotel.getRooms().add(room);
        return hotelRepository.save(hotel);
    }

    @Transactional
    public void removeRoomToHotel(int hotelId, int roomId) {
        Hotel hotel = findById(hotelId);
        Room room = roomService.findById(roomId);
        hotel.getRooms().remove(room);
        room.setHotel(null);
        roomService.updateRoom(room);
        hotelRepository.save(hotel);
    }

    @Transactional
    public Hotel addEmployeeToHotel(int hotelId, int employeeId) {
        Hotel hotel = findById(hotelId);
        Employee employee = employeeService.findById(employeeId);
        employee.setHotel(hotel);
        hotel.getEmployees().add(employee);
        return hotelRepository.save(hotel);
    }

    @Transactional
    public void removeEmployeeToHotel(int hotelId, int employeeId) {
        Hotel hotel = findById(hotelId);
        Employee employee = employeeService.findById(employeeId);
        hotel.getEmployees().remove(employee);
        employee.setHotel(null);
        employeeService.updateEmployee(employee);
        hotelRepository.save(hotel);
    }

    private Hotel findById(int hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(
                () -> new IllegalStateException("Hotel with id " + hotelId + " does not exist"));
    }
}
