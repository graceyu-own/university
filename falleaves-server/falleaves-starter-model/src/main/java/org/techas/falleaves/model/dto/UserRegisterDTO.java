package org.techas.falleaves.model.dto;

public class UserRegisterDTO{

    private String nickname;
    private String email;
    private String password;

    public String getNickname() {
        return nickname;
    }

    public UserRegisterDTO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}