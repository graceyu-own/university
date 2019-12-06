package cn.falleaves.auth.model;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private String phone;
    private Long age;
    private String location;
    private java.sql.Timestamp birthday;
    private java.sql.Timestamp createAt;
    private java.sql.Timestamp updateAt;


    public Long getId() {
        return id;
    }

    public UserInfo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserInfo setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Long getAge() {
        return age;
    }

    public UserInfo setAge(Long age) {
        this.age = age;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public UserInfo setLocation(String location) {
        this.location = location;
        return this;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public UserInfo setBirthday(Timestamp birthday) {
        this.birthday = birthday;
        return this;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public UserInfo setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
        return this;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public UserInfo setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
        return this;
    }
}
