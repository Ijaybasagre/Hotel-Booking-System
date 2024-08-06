package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.Booking;
import com.projects.Hotel_Booking_System.Model.Payment;
import com.projects.Hotel_Booking_System.Model.Request.PaymentRequest;
import com.projects.Hotel_Booking_System.Repository.IBookingRepository;
import com.projects.Hotel_Booking_System.Repository.IPaymentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final IPaymentRepository paymentRepository;

    private final IBookingRepository bookingRepository;

    public PaymentService(IPaymentRepository paymentRepository, IBookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Payment> getAllPayments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return paymentRepository.findAll(pageable).getContent();
    }

    public Payment getPayment(int paymentId) {
        return findById(paymentId);
    }

    public Payment addPayment(PaymentRequest paymentRequest) {
        Booking booking = findBookingById(paymentRequest.getBookingId());
        Payment payment = Payment.builder()
                .amount(paymentRequest.getAmount())
                .paymentDate(paymentRequest.getPaymentDate())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .status(paymentRequest.getStatus())
                .booking(booking)
                .build();
        return paymentRepository.save(payment);
    }

    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(PaymentRequest payment, int paymentId) {
        Booking booking = findBookingById(payment.getBookingId());
        Payment existingPayment = findById(paymentId);
        existingPayment.setBooking(booking);
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setPaymentDate(payment.getPaymentDate());
        existingPayment.setPaymentMethod(payment.getPaymentMethod());
        existingPayment.setStatus(payment.getStatus());
        return paymentRepository.save(existingPayment);
    }

    public void deletePayment(int paymentId) {
        Payment payment = findById(paymentId);
        paymentRepository.delete(payment);
    }

    private Payment findById(int paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(
                () -> new IllegalArgumentException("Payment with id " + paymentId + " not found."));
    }

    private Booking findBookingById(int bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(
                () -> new IllegalArgumentException("Booking with id " + bookingId + " not found."));
    }
}
