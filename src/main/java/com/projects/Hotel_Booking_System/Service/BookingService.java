package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.Booking;
import com.projects.Hotel_Booking_System.Model.Enums.BookingStatus;
import com.projects.Hotel_Booking_System.Repository.IBookingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final IBookingRepository bookingRepository;

    public BookingService(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookingRepository.findAll(pageable).getContent();
    }

    public Booking getBooking(int bookingId) {
        return findById(bookingId);
    }

    public List<Booking> getAllBookingsByStatus(BookingStatus status) {
        return bookingRepository.findAllByStatus(status);
    }

    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Booking booking, int bookingId) {
        Booking existingBooking = findById(bookingId);
        existingBooking.setGuest(booking.getGuest());
        existingBooking.setRoom(booking.getRoom());
        existingBooking.setCheckInDate(booking.getCheckInDate());
        existingBooking.setCheckOutDate(booking.getCheckOutDate());
        existingBooking.setReservationDate(booking.getReservationDate());
        existingBooking.setTotalPrice(booking.getTotalPrice());
        existingBooking.setPayment(booking.getPayment());
        existingBooking.setStatus(booking.getStatus());
        existingBooking.setFeedback(booking.getFeedback());
        return bookingRepository.save(existingBooking);
    }

    public void deleteBooking(int bookingId) {
        Booking booking = findById(bookingId);
        bookingRepository.delete(booking);
    }


    private Booking findById(int bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(
                () -> new IllegalArgumentException("Booking with id " + bookingId + " not found.")
        );
    }
}
