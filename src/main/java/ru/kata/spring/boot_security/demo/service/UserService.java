package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    public User findById(long id);

    public User findUserByUsername(String username);

    public List<User> findAll();

    public User saveUser(User user);

    public void deleteById(long id);

    //public List<Role> getListOfRoles();

}
