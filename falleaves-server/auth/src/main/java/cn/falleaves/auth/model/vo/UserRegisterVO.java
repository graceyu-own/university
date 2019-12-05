package cn.falleaves.auth.model.vo;

public class UserRegisterVO {

    private String nickname;
    private String email;
    private String password;
    private String password2;

    public UserRegisterVO(String nickname, String email, String password, String password2) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.password2 = password2;
    }

    public String getNickname() {
        return nickname;
    }

    public UserRegisterVO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterVO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterVO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword2() {
        return password2;
    }

    public UserRegisterVO setPassword2(String password2) {
        this.password2 = password2;
        return this;
    }
}
