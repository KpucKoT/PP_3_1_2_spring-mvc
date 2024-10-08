package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    void addRole(Role role);

    List<Role> findAll();

    Set<Role> getByUsername(String username);
}

