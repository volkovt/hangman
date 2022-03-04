package com.volkovt.hangmanapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.volkovt.hangmanapi.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
