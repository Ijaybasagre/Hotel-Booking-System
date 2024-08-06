package com.projects.Hotel_Booking_System.Repository;

import com.projects.Hotel_Booking_System.Model.Employee;
import com.projects.Hotel_Booking_System.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {

   List<Employee> findByRole(Role role);

   List<Employee> findByNameContainsIgnoreCase(String name);

}
