package com.cdis.microservice.example.auth.service;

import com.cdis.microservice.example.auth.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findRoles();
    public Role findRoleById(Long id);

    public Role addRole(Role role);

    public void updateRole(Role role);

    public void deleteRole(Role role);
    public void deleteRolebyId(Long id);

}
