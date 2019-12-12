package org.techas.falleaves.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_info", schema = "falleaves", catalog = "")
public class UserInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;
    private String nickname;
    private String email;
    private String phone;
    private Long age;
    private String location;
    private java.sql.Timestamp birthday;


    public Long getId() {
        return id;
    }

    public UserInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUid() {
        return uid;
    }

    public UserInfoEntity setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserInfoEntity setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfoEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserInfoEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Long getAge() {
        return age;
    }

    public UserInfoEntity setAge(Long age) {
        this.age = age;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public UserInfoEntity setLocation(String location) {
        this.location = location;
        return this;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public UserInfoEntity setBirthday(Timestamp birthday) {
        this.birthday = birthday;
        return this;
    }
}
