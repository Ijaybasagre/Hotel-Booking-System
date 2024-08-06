package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.Booking;
import com.projects.Hotel_Booking_System.Model.Enums.BookingStatus;
import com.projects.Hotel_Booking_System.Model.Guest;
import com.projects.Hotel_Booking_System.Model.Payment;
import com.projects.Hotel_Booking_System.Model.Request.BookingRequest;
import com.projects.Hotel_Booking_System.Model.Response.BookingResponse;
import com.projects.Hotel_Booking_System.Model.Room;
import com.projects.Hotel_Booking_System.Repository.IBookingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final IBookingRepository bookingRepository;

    private final RoomService roomService;

    private final GuestService guestService;

    private final PaymentService paymentService;

    public BookingService(IBookingRepository bookingRepository, RoomService roomService, GuestService guestService, PaymentService paymentService) {
        this.bookingRepository = bookingRepository;
        this.roomService = roomService;
        this.guestService = guestService;
        this.paymentService = paymentService;
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

    public List<Booking> getBookingsByCheckInDate(LocalDate checkInDate) {
        return bookingRepository.findByCheckInDate(checkInDate);
    }

    @Transactional
    public BookingResponse addBooking(BookingRequest bookingRequest) {
        Room availableRoom = roomService.getRoomByTypeAndStatus(bookingRequest.getRoomType().getCode(), "AVAILABLE");
        Guest guest = guestService.getGuest(bookingRequest.getGuest().getId());
        Booking booking =
                Booking.builder()
                        .checkInDate(bookingRequest.getCheckInDate())
                        .checkOutDate(bookingRequest.getCheckOutDate())
                        .checkOutTime(bookingRequest.getCheckOutTime())
                        .reservationDate(bookingRequest.getReservationDate())
                        .totalPrice(bookingRequest.getTotalPrice())
                        .status(bookingRequest.getStatus())
                        .guest(guest)
                        .room(availableRoom)
                        .build();

        List<Booking> guestBookings = guest.getBookings();
        guestBookings.add(booking);
        guest.setBookings(guestBookings);

        guestService.addGuest(guest);
        bookingRepository.save(booking);


        Payment payment;
        if (bookingRequest.getPayment() != null) {
            payment = bookingRequest.getPayment();
            payment.setBooking(booking);
            paymentService.addPayment(payment);
            booking.setPayment(payment);
            bookingRepository.save(booking);
        }

        return BookingResponse.builder()
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .checkOutTime(booking.getCheckOutTime())
                .reservationDate(booking.getReservationDate())
                .totalPrice(booking.getTotalPrice())
                .status(booking.getStatus())
                .room(booking.getRoom())
                .guest(booking.getGuest())
                .payment(booking.getPayment())
                .build();
    }

    @Transactional
    public Booking updateBooking(Booking booking, int bookingId) {
        Booking existingBooking = findById(bookingId);
        existingBooking.setRoom(booking.getRoom());
        existingBooking.setCheckInDate(booking.getCheckInDate());
        existingBooking.setCheckOutDate(booking.getCheckOutDate());
        existingBooking.setReservationDate(booking.getReservationDate());
        existingBooking.setTotalPrice(booking.getTotalPrice());
        existingBooking.setPayment(booking.getPayment());
        existingBooking.setStatus(booking.getStatus());
        existingBooking.setFeedback(booking.getFeedback());
        Payment payment = booking.getPayment();
        payment.setBooking(existingBooking);
        paymentService.addPayment(payment);
        return bookingRepository.save(existingBooking);
    }

    @Transactional
    public Booking addBookingPayment(Payment payment, int bookingId) {
        Booking existingBooking = findById(bookingId);
        existingBooking.setPayment(payment);
        payment.setBooking(existingBooking);
        paymentService.addPayment(payment);
        return bookingRepository.save(existingBooking);
    }

    @Transactional
    public Booking updateBookingPayment(Payment payment, int bookingId, int paymentId) {
        Booking existingBooking = findById(bookingId);
        Payment existingPayment = paymentService.getPayment(paymentId);
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setPaymentMethod(payment.getPaymentMethod());
        existingPayment.setPaymentDate(payment.getPaymentDate());
        existingPayment.setBooking(existingBooking);
        existingBooking.setPayment(existingPayment);
        paymentService.addPayment(existingPayment);
        return bookingRepository.save(existingBooking);
    }

    @Transactional
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
