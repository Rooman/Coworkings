package com.javastudy.coworkings.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private long id;
    private String username;
    private String password;
    private String fullName;
    private UserRole userRole;
    private String tel;
    private String email;
    private String city;
    private String sole;

    /*private User() {
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getSole() {
        return sole;
    }

    public static UserBuilder newBuilder() {
        return new User().new UserBuilder();
    }

    public class UserBuilder {

        private UserBuilder() {
        }

        public UserBuilder setId(Long id) {
            User.this.id = id;
            return this;
        }

        public UserBuilder setUsername(String username) {
            User.this.username = username;
            return this;
        }

        public UserBuilder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public UserBuilder setFullName(String fullName) {
            User.this.fullName = fullName;
            return this;
        }

        public UserBuilder setUserRole(UserRole userRole) {
            User.this.userRole = userRole;
            return this;
        }

        public UserBuilder setTel(String tel) {
            User.this.tel = tel;
            return this;
        }

        public UserBuilder setEmail(String email) {
            User.this.email = email;
            return this;
        }

        public UserBuilder setCity(String city) {
            User.this.city = city;
            return this;
        }

        public UserBuilder setSole(String sole) {
            User.this.sole = sole;
            return this;
        }

        public User build() {
            return User.this;
        }
    }*/
}
