package com.projects.Hotel_Booking_System.Controller;

import com.projects.Hotel_Booking_System.Model.Hotel;
import com.projects.Hotel_Booking_System.Service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;


    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllHotels(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(hotelService.getAllHotels(page, size));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getAllHotels(@PathVariable int hotelId) {
        return ResponseEntity.ok(hotelService.getHotel(hotelId));
    }

    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.addHotel(hotel));
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable int hotelId) {
        return ResponseEntity.ok(hotelService.updateHotel(hotel, hotelId));
    }


    @PutMapping("/{hotelId}/rooms/{roomId}")
    public ResponseEntity<Hotel> addRoomToHotel(@PathVariable int hotelId, @PathVariable int roomId) {
        return ResponseEntity.ok(hotelService.addRoomToHotel(hotelId, roomId));
    }

    @DeleteMapping("/{hotelId}/rooms/{roomId}")
    public ResponseEntity<Hotel> removeRoomFromHotel(@PathVariable int hotelId, @PathVariable int roomId) {
        hotelService.removeRoomToHotel(hotelId, roomId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{hotelId}/employees/{employeeId}")
    public ResponseEntity<Hotel> addEmployeeToHotel(@PathVariable int hotelId, @PathVariable int employeeId) {
        return ResponseEntity.ok(hotelService.addEmployeeToHotel(hotelId, employeeId));
    }

    @DeleteMapping("/{hotelId}/employees/{employeeId}")
    public ResponseEntity<Hotel> removeEmployeeFromHotel(@PathVariable int hotelId, @PathVariable int employeeId) {
        hotelService.removeEmployeeToHotel(hotelId, employeeId);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Hotel> deleteHotel(@PathVariable int hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok().build();
    }
}
