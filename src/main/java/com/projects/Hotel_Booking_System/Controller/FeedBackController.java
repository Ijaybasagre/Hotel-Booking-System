package com.projects.Hotel_Booking_System.Controller;

import com.projects.Hotel_Booking_System.Model.FeedBack;
import com.projects.Hotel_Booking_System.Service.FeedBackService;
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
@RequestMapping("/feedbacks")
public class FeedBackController {

    private final FeedBackService feedBackService;

    public FeedBackController(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @GetMapping
    public ResponseEntity<List<FeedBack>> getAllFeedBacks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(feedBackService.getAllFeedBacks(page, size));
    }

    @GetMapping("/{feedBackId}")
    public ResponseEntity<FeedBack> getFeedBack(@PathVariable int feedBackId) {
        return ResponseEntity.ok(feedBackService.getFeedBack(feedBackId));
    }

    @PostMapping
    public ResponseEntity<FeedBack> addFeedBack(@RequestBody FeedBack feedBack) {
        return ResponseEntity.ok(feedBackService.addFeedBack(feedBack));
    }

    @PutMapping("/{feedBackId}")
    public ResponseEntity<FeedBack> updateFeedBack(@RequestBody FeedBack feedBack, @PathVariable int feedBackId) {
        return ResponseEntity.ok(feedBackService.updateFeedBack(feedBack, feedBackId));
    }

    @DeleteMapping("/{feedBackId}")
    public ResponseEntity<Object> deleteFeedBack(@PathVariable int feedBackId) {
        feedBackService.deleteFeedBack(feedBackId);
        return ResponseEntity.ok().build();
    }
}
