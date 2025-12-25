package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    private String role;

    public User() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final User u = new User();
        public Builder id(Long v){u.id=v;return this;}
        public Builder name(String v){u.name=v;return this;}
        public Builder email(String v){u.email=v;return this;}
        public Builder password(String v){u.password=v;return this;}
        public Builder role(String v){u.role=v;return this;}
        public User build(){return u;}
    }
}
