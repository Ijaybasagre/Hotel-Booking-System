package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.Guest;
import com.projects.Hotel_Booking_System.Repository.IGuestRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {


    private final IGuestRepository guestRepository;

    public GuestService(IGuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getAllGuests(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return guestRepository.findAll(pageable).getContent();
    }

    public Guest getGuest(int id) {
        return findById(id);
    }

    public Guest addGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public Guest updateGuest(Guest guest, int id) {
        Guest guestToBeUpdate = findById(id);
        guestToBeUpdate.setName(guest.getName());
        guestToBeUpdate.setAddress(guest.getAddress());
        guestToBeUpdate.setContactNumber(guest.getContactNumber());
        guestToBeUpdate.setEmail(guest.getEmail());
        guestToBeUpdate.setNationality(guest.getNationality());
        guestToBeUpdate.setBookings(guest.getBookings());
        return guestRepository.save(guest);
    }

    public void deleteGuest(int id) {
        findById(id);
        guestRepository.deleteById(id);
    }
    private Guest findById(int id) {
        return guestRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Guest with id " + id + " does not exist"));
    }
}
