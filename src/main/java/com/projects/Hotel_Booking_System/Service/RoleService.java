package com.projects.Hotel_Booking_System.Service;

import com.projects.Hotel_Booking_System.Model.Role;
import com.projects.Hotel_Booking_System.Repository.IRoleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roleRepository.findAll(pageable).getContent();
    }

    public Role getRole(int id) {
        return findById(id);
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Role role, int id) {
        Role existingRole = findById(id);
        existingRole.setName(role.getName());
        existingRole.setCode(role.getCode());
        existingRole.setEmployees(role.getEmployees());
        return roleRepository.save(existingRole);
    }

    public void deleteRole(int id) {
        findById(id);
        roleRepository.deleteById(id);
    }

    public Role findByRoleNameOrCode(String role, String code) {
        return roleRepository.findByNameOrCodeIgnoreCase(role, code);
    }

    private Role findById(int id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Role with id " + id + " does not exist"));
    }
}
