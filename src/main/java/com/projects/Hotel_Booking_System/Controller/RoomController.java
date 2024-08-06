package com.projects.Hotel_Booking_System.Controller;

import com.projects.Hotel_Booking_System.Model.Room;
import com.projects.Hotel_Booking_System.Service.RoomService;
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
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(roomService.getAllRooms(page, size));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoom(@PathVariable int roomId) {
        return ResponseEntity.ok(roomService.getRoom(roomId));
    }

    @GetMapping("/number={roomNumber}")
    public ResponseEntity<Room> getRoomByRoomNumber(@PathVariable int roomNumber) {
        return ResponseEntity.ok(roomService.getRoomByRoomNumber(roomNumber));
    }

    @GetMapping("/status={status}")
    public ResponseEntity<List<Room>> getRoom(@PathVariable String status) {
        return ResponseEntity.ok(roomService.getRoomByStatus(status));
    }

    @GetMapping("/")
    public ResponseEntity<List<Room>> getRoom(@RequestParam String code, @RequestParam String status) {
        return ResponseEntity.ok(roomService.getRoomsByTypeAndStatus(code, status));
    }

    @PostMapping
    public ResponseEntity<Room> getRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.addRoom(room));
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room, @PathVariable int roomId) {
        return ResponseEntity.ok(roomService.updateRoom(room, roomId));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.ok().build();
    }
}
