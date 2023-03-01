package com.example.mobilelewebapp.services.impl;

import com.example.mobilelewebapp.models.entities.Role;
import com.example.mobilelewebapp.models.entities.enums.RoleType;
import com.example.mobilelewebapp.repositories.RoleRepository;
import com.example.mobilelewebapp.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addAll() {
        roleRepository.saveAll(
                Arrays.stream(RoleType.values())
                        .map(Role::new)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public boolean repositoryIsEmpty() {
        return roleRepository.count() == 0;
    }
}
