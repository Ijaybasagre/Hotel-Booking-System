package com.projects.Hotel_Booking_System.Model.Enums;

public enum RoomStatus {
    AVAILABLE,
    OCCUPIED,
    RESERVED,
    UNAVAILABLE;

     public static RoomStatus getStatus(String status){
         return switch (status) {
             case "AVAILABLE" -> RoomStatus.AVAILABLE;
             case "OCCUPIED" -> RoomStatus.OCCUPIED;
             case "RESERVED" -> RoomStatus.RESERVED;
             case "UNAVAILABLE" -> RoomStatus.UNAVAILABLE;
             default ->  throw new IllegalArgumentException("Invalid status: " + status);
         };
     }
}
