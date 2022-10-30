package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByTitle(String title);
}
