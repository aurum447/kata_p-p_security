package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    @Override
    public User findById(long id) {
        return userRepository.getOne(id);
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        String passwordFromUser = user.getPassword();

        if(findUserByUsername(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(passwordFromUser));
            return userRepository.save(user);
        }

        String passwordFromDB = findUserByUsername(user.getUsername()).getPassword();

        if(!passwordFromUser.equals(passwordFromDB)) {
            user.setPassword(passwordEncoder.encode(passwordFromUser));
        }
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return user;
    }

/*    @Transactional
    @Override
    public void update(User user) {
        User userPrimary = findById(user.getId());
        //System.out.println(userPrimary);
        //System.out.println(user);
        if(!userPrimary.getPassword().equals(user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        }
        userRepository.save(user);
    }*/

/*    @Transactional
    @Override
    public List<Role> getListOfRoles() {
        return roleRepository.findAll();
    }*/

}
