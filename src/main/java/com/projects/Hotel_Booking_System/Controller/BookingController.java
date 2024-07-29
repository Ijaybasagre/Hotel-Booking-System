package com.projects.Hotel_Booking_System.Controller;

import com.projects.Hotel_Booking_System.Model.Booking;
import com.projects.Hotel_Booking_System.Model.Enums.BookingStatus;
import com.projects.Hotel_Booking_System.Service.BookingService;
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

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(bookingService.getAllBookings(page, size));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable int bookingId) {
        return ResponseEntity.ok(bookingService.getBooking(bookingId));
    }

    @GetMapping("/status={bookingStatus}")
    public ResponseEntity<List<Booking>> getBooking(@PathVariable BookingStatus bookingStatus) {
        return ResponseEntity.ok(bookingService.getAllBookingsByStatus(bookingStatus));
    }

//    @GetMapping
//    public ResponseEntity<List<Booking>> getBookingBetweenCheckInDateAndCheckOutDate(@RequestParam String checkInDate, @RequestParam String checkOutDate) {
//        return ResponseEntity.ok(bookingService.getBookingBetweenCheckInDateAndCheckOutDate(checkInDate, checkOutDate));
//    }


    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.addBooking(booking));
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking, @PathVariable int bookingId) {
        return ResponseEntity.ok(bookingService.updateBooking(booking, bookingId));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Object> deleteBooking(@PathVariable int bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok().build();
    }


}
