package com.projects.Hotel_Booking_System.Controller;

import com.projects.Hotel_Booking_System.Model.Employee;
import com.projects.Hotel_Booking_System.Service.EmployeeService;
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
@RequestMapping("/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(employeeService.getAllEmployees(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @GetMapping("/name={employeeName}")
    public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable String employeeName) {
        return ResponseEntity.ok(employeeService.getEmployeeByName(employeeName));
    }

    @GetMapping("/role={role}/code={code}")
    public ResponseEntity<List<Employee>> getEmployeeByRoleCode(@PathVariable(required = false) String role, @PathVariable(required = false) String code) {
        return ResponseEntity.ok(employeeService.getEmployeeByRole(role, code));
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable int id) {
        return ResponseEntity.ok(employeeService.updateEmployee(employee, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
