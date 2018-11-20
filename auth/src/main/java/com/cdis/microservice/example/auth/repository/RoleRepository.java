package com.cdis.microservice.example.auth.repository;

import com.cdis.microservice.example.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
