package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {

    public User findById(long id);

    public List<User> findAll();

    public User saveUser(User user);

    public void deleteById(long id);

    public List<Role> getListOfRoles();

}