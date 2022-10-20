package com.example.springbootbuilidingblock.entities;

import javax.persistence.*;

@Entity
// Entity is the table in the sql table
// class name is the default entity name
// entity name can also be changed using the @Entity(name = "entity name")
@Table(name = "users")
public class User {
    // fields
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "user_name", length = 50, nullable = false, unique = true)
    private String username;
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstname;
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastname;
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;
    @Column(name = "role", length = 50, nullable = false)
    private String role;
    @Column(name = "ssn", length = 11, nullable = false, unique = true)
    private String ssn;

    // no ar constructor (required for an entity)
    public User() {
    }

    // fields constructor (required for an entity)
    public User(long id, String username, String firstname, String lastname, String email, String role, String ssn) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNin() {
        return ssn;
    }

    public void setNin(String nin) {
        this.ssn = nin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }
}
