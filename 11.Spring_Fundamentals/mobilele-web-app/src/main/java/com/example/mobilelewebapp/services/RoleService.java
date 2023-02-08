package com.example.mobilelewebapp.services;

import com.example.mobilelewebapp.models.dtos.RoleType;
import com.example.mobilelewebapp.models.entities.Role;

public interface RoleService {

    RoleType[] getAllRoleTypes();

    Role getRoleUser();
}
