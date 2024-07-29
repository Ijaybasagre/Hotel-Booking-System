package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.FeedBack;
import com.projects.Hotel_Booking_System.Repository.IFeedBackRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackService {

    private final IFeedBackRepository feedBackRepository;

    public FeedBackService(IFeedBackRepository feedBackRepository) {
        this.feedBackRepository = feedBackRepository;
    }

    public List<FeedBack> getAllFeedBacks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return feedBackRepository.findAll(pageable).getContent();
    }

    public FeedBack getFeedBack(int feedBackId) {
        return findById(feedBackId);
    }

    public FeedBack addFeedBack(FeedBack feedBack) {
        return feedBackRepository.save(feedBack);
    }

    public FeedBack updateFeedBack(FeedBack feedBack, int feedBackId) {
        FeedBack existingFeedBack = findById(feedBackId);
        existingFeedBack.setBooking(feedBack.getBooking());
        existingFeedBack.setComment(feedBack.getComment());
        existingFeedBack.setRating(feedBack.getRating());
        existingFeedBack.setDateSubmitted(feedBack.getDateSubmitted());
        return feedBackRepository.save(existingFeedBack);
    }

    public void deleteFeedBack(int feedBackId) {
        FeedBack feedBack = findById(feedBackId);
        feedBackRepository.delete(feedBack);
    }

    private FeedBack findById(int feedBackId) {
        return feedBackRepository.findById(feedBackId).orElseThrow(
                () -> new IllegalArgumentException("FeedBack with id " + feedBackId + " not found."));
    }
}
