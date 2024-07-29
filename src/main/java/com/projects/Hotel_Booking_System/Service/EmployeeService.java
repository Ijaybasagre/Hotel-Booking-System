package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.Employee;
import com.projects.Hotel_Booking_System.Repository.IEmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable).getContent();
    }

    public Employee getEmployee(int id) {
        return findById(id);
    }

    public List<Employee> getEmployeeByRole(String role) {
        return employeeRepository.findByRole(role);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee, int id) {
        Employee existingEmployee = findById(id);
        existingEmployee.setName(employee.getName());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setContactNumber(employee.getContactNumber());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setHotel(employee.getHotel());
        existingEmployee.setRole(employee.getRole());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int id) {
        findById(id);
        employeeRepository.deleteById(id);
    }

    protected Employee findById(int id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Employee with id " + id + " does not exist"));
    }
}
