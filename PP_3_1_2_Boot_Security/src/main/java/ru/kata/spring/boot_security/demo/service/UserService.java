package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User findByUsername(String username);

    void createUser(User user);

    void updateUser(int id, User user);

    void deleteUser(int id);

    User getUserById(int id);
}
