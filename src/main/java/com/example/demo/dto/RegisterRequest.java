package com.example.demo.dto;

public class RegisterRequest {

    private String name;
    private String email;
    private String password;

    public RegisterRequest() {}

    // ✅ getters (required)
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // builder (tests expect this)
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

        public RegisterRequest build() {
            return r;
        }
    }
}
