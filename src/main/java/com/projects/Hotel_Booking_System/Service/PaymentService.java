package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.Payment;
import com.projects.Hotel_Booking_System.Repository.IPaymentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final IPaymentRepository paymentRepository;

    public PaymentService(IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return paymentRepository.findAll(pageable).getContent();
    }

    public Payment getPayment(int paymentId) {
        return findById(paymentId);
    }

    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Payment payment, int paymentId) {
        Payment existingPayment = findById(paymentId);
        existingPayment.setBooking(payment.getBooking());
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
}
