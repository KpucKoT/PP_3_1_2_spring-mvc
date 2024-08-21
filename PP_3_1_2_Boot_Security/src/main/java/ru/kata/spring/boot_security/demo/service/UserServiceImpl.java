package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    UserRepository userRepository;

    RoleRepository roleRepository;

    BCryptPasswordEncoder encoder;

    private RoleService roleService;

    @Autowired
    public UserServiceImpl(RoleService roleService, UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }


    public UserServiceImpl() {
    }

    @Transactional
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public User getUserById(int id) {
        Optional<User> employee = userRepository.findById(id);
        return employee.orElse(new User());
    }

    @Transactional
    @Override
    public void createUser(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(roleService.getByUsername("USER"));
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(int id, User user) {
        try {
            User user0 = getUserById(id);
            user0.setUsername(user.getUsername());
            user0.setLastName(user.getLastName());
            user0.setPassword(encoder.encode(user.getPassword()));
            user0.setEmail(user.getEmail());
            user0.setCar_id(user.getCar_id());
            user0.setRoles(user.getRoles());
            userRepository.save(user0);
        } catch (NullPointerException e) {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
