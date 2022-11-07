package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


public interface UserService extends UserDetailsService {

    public List<User> getUsers();

    public User findById(long id);

    public User findUserByUsername(String username);

    public void saveUser(User user);

    public void deleteById(long id);

    public void update(long id, User user);

}
