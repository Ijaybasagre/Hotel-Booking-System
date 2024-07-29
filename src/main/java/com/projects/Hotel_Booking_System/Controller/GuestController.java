package com.projects.Hotel_Booking_System.Controller;

import com.projects.Hotel_Booking_System.Model.Guest;
import com.projects.Hotel_Booking_System.Service.GuestService;
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
@RequestMapping("/guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public ResponseEntity<List<Guest>> getAllGuest(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(guestService.getAllGuests(page, size));
    }

    @GetMapping("/{guestId}")
    public ResponseEntity<Guest> getGuest(@PathVariable int guestId) {
        return ResponseEntity.ok(guestService.getGuest(guestId));
    }

    @PostMapping
    public ResponseEntity<Guest> addGuest(@RequestBody Guest guest) {
        return ResponseEntity.ok(guestService.addGuest(guest));
    }

    @PutMapping("/{guestId}")
    public ResponseEntity<Guest> updateGuest(@RequestBody Guest guest,@PathVariable int guestId) {
        return ResponseEntity.ok(guestService.updateGuest(guest, guestId));
    }

    @DeleteMapping("/{guestId}")
    public ResponseEntity<Object> deleteGuest(@PathVariable int guestId) {
        guestService.deleteGuest(guestId);
        return ResponseEntity.ok().build();
    }
}

