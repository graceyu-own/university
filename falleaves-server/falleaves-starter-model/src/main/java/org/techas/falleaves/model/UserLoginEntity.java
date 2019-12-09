package org.techas.falleaves.model;

import javax.persistence.*;

@Entity
@Table(name = "user_login", schema = "falleaves", catalog = "")
public class UserLoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;
    private String identifier;
    private String credential;

    public Long getId() {
        return id;
    }

    public UserLoginEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUid() {
        return uid;
    }

    public UserLoginEntity setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public String getIdentifier() {
        return identifier;
    }

    public UserLoginEntity setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public String getCredential() {
        return credential;
    }

    public UserLoginEntity setCredential(String credential) {
        this.credential = credential;
        return this;
    }
}

