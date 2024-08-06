package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.Employee;
import com.projects.Hotel_Booking_System.Model.Role;
import com.projects.Hotel_Booking_System.Repository.IEmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final IEmployeeRepository employeeRepository;

    private final RoleService roleService;

    public EmployeeService(IEmployeeRepository employeeRepository, RoleService roleService) {
        this.employeeRepository = employeeRepository;
        this.roleService = roleService;
    }

    public List<Employee> getAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable).getContent();
    }

    public Employee getEmployee(int id) {
        return findById(id);
    }

    public List<Employee> getEmployeeByRole(String roleName, String roleCode) {
        Role role = roleService.findByRoleNameOrCode(roleName, roleCode);
        return employeeRepository.findByRole(role);
    }

    public List<Employee> getEmployeeByName(String employeeName) {
        return employeeRepository.findByNameContainsIgnoreCase(employeeName);
    }

    @Transactional
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Employee employee, int id) {
        Employee existingEmployee = findById(id);
        existingEmployee.setName(employee.getName());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setContactNumber(employee.getContactNumber());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setHotel(employee.getHotel());
        existingEmployee.setRole(employee.getRole());
        return employeeRepository.save(existingEmployee);
    }

    @Transactional
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(int id) {
        findById(id);
        employeeRepository.deleteById(id);
    }

    protected Employee findById(int id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Employee with id " + id + " does not exist"));
    }
}
