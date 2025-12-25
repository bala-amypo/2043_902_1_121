package com.example.demo.dto;

public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String role;   // ✅ REQUIRED BY TESTS

    public RegisterRequest() {}

    // ✅ GETTERS (important for controllers & tests)
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final RegisterRequest r = new RegisterRequest();

        public Builder name(String name) {
            r.name = name;
            return this;
        }

        public Builder email(String email) {
            r.email = email;
            return this;
        }

        public Builder password(String password) {
            r.password = password;
            return this;
        }

        // ✅ THIS FIXES MANY FAILURES
        public Builder role(String role) {
            r.role = role;
            return this;
        }

        public RegisterRequest build() {
            return r;
        }
    }
}
