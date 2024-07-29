package com.projects.Hotel_Booking_System.Controller;

import com.projects.Hotel_Booking_System.Model.RoomType;
import com.projects.Hotel_Booking_System.Service.RoomTypeService;
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

@RestController
@RequestMapping("/roomTypes")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }


    @GetMapping
    public ResponseEntity<Object> getAllRoomTypes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(roomTypeService.getAllRoomTypes(page, size));
    }

    @GetMapping("/{roomTypeId}")
    public ResponseEntity<Object> getRoomType(@PathVariable int roomTypeId) {
        return ResponseEntity.ok(roomTypeService.getRoomType(roomTypeId));
    }

    @PostMapping
    public ResponseEntity<Object> addRoomType(@RequestBody RoomType roomType) {
        return ResponseEntity.ok(roomTypeService.addRoomType(roomType));
    }

    @PutMapping("/{roomTypeId}")
    public ResponseEntity<Object> updateRoomType(@RequestBody RoomType roomType, @PathVariable int roomTypeId) {
        return ResponseEntity.ok(roomTypeService.updateRoomType(roomType, roomTypeId));
    }

    @DeleteMapping("/{roomTypeId}")
    public ResponseEntity<Object> deleteRoomType(@PathVariable int roomTypeId) {
        roomTypeService.deleteRoomType(roomTypeId);
        return ResponseEntity.ok().build();
    }
}
