package org.techas.falleaves.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public void setId(Long id) {
        this.id = id;
    }


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public java.sql.Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Timestamp birthday) {
        this.birthday = birthday;
    }

}
