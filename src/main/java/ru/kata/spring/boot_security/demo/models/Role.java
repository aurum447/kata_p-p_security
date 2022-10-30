package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;


@Table(name = "roles")
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Role() {}
    public Role(long id, String title) {
        this.id = id;
        this.title = title;
    }

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    @Override
    public String getAuthority() {
        return getTitle();
    }
}
