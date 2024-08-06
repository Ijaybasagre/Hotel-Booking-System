package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.Rate;
import com.projects.Hotel_Booking_System.Repository.IRateRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {


    private final IRateRepository rateRepository;

    public RateService(IRateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public List<Rate> getAllRates(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return rateRepository.findAll(pageable).getContent();
    }

    public Rate getRate(int id) {
        return findById(id);
    }


    public Rate addRate(Rate rate) {
        return rateRepository.save(rate);
    }

    public Rate updateRate(Rate rate, int id) {
        Rate rateToBeUpdate = findById(id);
        rateToBeUpdate.setPrice(rate.getPrice());
        rateToBeUpdate.setValidFrom(rate.getValidFrom());
        rateToBeUpdate.setValidTo(rate.getValidTo());
        rateToBeUpdate.setRoomType(rate.getRoomType());
        return rateRepository.save(rateToBeUpdate);
    }

    public void deleteRate(int id) {
        rateRepository.deleteById(id);
    }


    private Rate findById(int id) {
        return rateRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Rate with id " + id + " does not exist"));
    }


}
