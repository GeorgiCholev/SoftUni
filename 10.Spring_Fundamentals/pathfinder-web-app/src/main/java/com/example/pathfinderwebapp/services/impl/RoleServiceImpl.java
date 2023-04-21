package com.example.pathfinderwebapp.services.impl;

import com.example.pathfinderwebapp.models.entities.Role;
import com.example.pathfinderwebapp.models.entities.enums.RoleType;
import com.example.pathfinderwebapp.repositories.RoleRepository;
import com.example.pathfinderwebapp.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRole(RoleType desired) {
        return roleRepository.findByType(desired);
    }
}
