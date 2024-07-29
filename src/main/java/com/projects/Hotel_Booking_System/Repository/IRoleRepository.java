package com.projects.Hotel_Booking_System.Repository;

import com.projects.Hotel_Booking_System.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Integer> {
}
