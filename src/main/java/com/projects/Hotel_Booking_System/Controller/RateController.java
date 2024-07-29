package com.projects.Hotel_Booking_System.Controller;

import com.projects.Hotel_Booking_System.Model.Rate;
import com.projects.Hotel_Booking_System.Service.RateService;
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
@RequestMapping("/rates")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping
    public ResponseEntity<List<Rate>> getAllRates(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(rateService.getAllRates(page, size));
    }

    @GetMapping("/{rateId}")
    public ResponseEntity<Rate> getRate(@PathVariable int rateId) {
        return ResponseEntity.ok(rateService.getRate(rateId));
    }


    @PostMapping
    public ResponseEntity<Rate> addRate(@RequestBody Rate rate) {
        return ResponseEntity.ok(rateService.addRate(rate));
    }


    @PutMapping("/{rateId}")
    public ResponseEntity<Rate> updateRate(@RequestBody Rate rate, @PathVariable int rateId) {
        return ResponseEntity.ok(rateService.updateRate(rate, rateId));
    }

    @DeleteMapping("/{rateId}")
    public ResponseEntity<Object> deleteRate(@PathVariable int rateId) {
        rateService.deleteRate(rateId);
        return ResponseEntity.ok().build();
    }
}
