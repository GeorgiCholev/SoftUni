package com.example.mobilelewebapp.services.impl;

import com.example.mobilelewebapp.models.dtos.RoleType;
import com.example.mobilelewebapp.models.entities.Role;
import com.example.mobilelewebapp.repositories.RoleRepository;
import com.example.mobilelewebapp.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private static final long ROLE_USER_ID = 1L;
    private static final long ROLE_ADMIN_ID = 2L;

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleType[] getAllRoleTypes() {
        List<Role> allRoles = roleRepository.findAll();
        RoleType[] roleTypes = new RoleType[allRoles.size()];

        for (int i = 0; i < roleTypes.length; i++) {
            roleTypes[i] = new RoleType(allRoles.get(i).getUserRole().name());
        }

        return roleTypes;
    }

    @Override
    public Role getRoleUser() {
        return roleRepository.findById(ROLE_USER_ID).get();
    }
}