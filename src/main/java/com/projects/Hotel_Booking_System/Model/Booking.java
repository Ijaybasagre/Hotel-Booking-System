package com.projects.Hotel_Booking_System.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projects.Hotel_Booking_System.Model.Enums.BookingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "booking")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Date checkInDate;

    private Date checkOutDate;

    private Time checkOutTime;

    @Temporal(value = TemporalType.DATE)
    private Date reservationDate;
    private Double totalPrice;

    @Enumerated(value = EnumType.STRING)
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    @JsonIgnore
    private Guest guest;

    @OneToOne(mappedBy = "booking")
    private Payment payment;


    @OneToMany(mappedBy = "booking")
    private List<FeedBack> feedback;
}
