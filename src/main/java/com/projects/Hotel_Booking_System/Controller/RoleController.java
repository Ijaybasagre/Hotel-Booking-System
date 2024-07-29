package com.projects.Hotel_Booking_System.Controller;

import com.projects.Hotel_Booking_System.Model.Role;
import com.projects.Hotel_Booking_System.Service.RoleService;
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
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(roleService.getAllRoles(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable int id) {
        return ResponseEntity.ok(roleService.getRole(id));
    }

    @PostMapping
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.addRole(role));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable int id) {
        return ResponseEntity.ok(roleService.updateRole(role, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}
