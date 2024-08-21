package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.entities.Role;
import java.util.Set;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    Set<Role> findByUsername(String username);
}
