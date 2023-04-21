package com.example.pathfinderwebapp.services;

import com.example.pathfinderwebapp.models.entities.Role;
import com.example.pathfinderwebapp.models.entities.enums.RoleType;

public interface RoleService {

    Role getRole(RoleType desired);
}
