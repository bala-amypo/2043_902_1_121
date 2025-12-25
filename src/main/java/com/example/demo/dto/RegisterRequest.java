package com.example.demo.dto;

public class RegisterRequest {

    public String name;
    public String email;
    public String password;

    public RegisterRequest() {}

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
