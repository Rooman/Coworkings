package com.javastudy.coworkings.entity;


// Since Builder was added, do I need to keep setter methods User.class?
// Or better to remove them?
public class User {
    private long id;
    private String username;
    private String password;
    private String fullName;
    private UserRole role;
    // Not Sure if phone number should an int. Maybe it should be string?
    private String tel;
    private String email;
    private String city;
    private String sole;

    private User() {
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSole() {
        return sole;
    }

    public void setSole(String sole) {
        this.sole = sole;
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

        public UserBuilder setRole(UserRole role) {
            User.this.role = role;
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
    }
}
