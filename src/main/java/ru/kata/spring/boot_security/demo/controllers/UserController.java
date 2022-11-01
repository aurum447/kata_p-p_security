package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getCurrentUser(Model model, Principal principal) {
        User user =
                userService.findById(
                        userService.findUserByUsername(
                                principal.getName()).getId());

        model.addAttribute("user", user);
        return "user/home-page";
    }

    @GetMapping("/admin")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/users-list";
    }


    //  //  //  //  ////  //  //  //  ////  //  //  //  ////  //  //  //  //


    @GetMapping("/admin/user-create")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/user-create";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam Set<Role> roles) {
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }


    //  //  //  //  ////  //  //  //  ////  //  //  //  ////  //  //  //  //


    @GetMapping("/admin/user-check/{id}")
    public String getUserToCheck(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findById(id));
        return "admin/user-check";
    }


    //  //  //  //  ////  //  //  //  ////  //  //  //  ////  //  //  //  //


    @GetMapping("/admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id")long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }


    //  //  //  //  ////  //  //  //  ////  //  //  //  ////  //  //  //  //


    @GetMapping("/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", Role.values());
        return "admin/user-update";
    }

    @PatchMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") long id,
                             @RequestParam Set<Role> roles) {
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }


}
