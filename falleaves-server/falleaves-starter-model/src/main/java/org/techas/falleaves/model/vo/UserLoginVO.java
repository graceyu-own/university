package org.techas.falleaves.model.vo;

public class UserLoginVO {

    private String identifier;
    private String credential;

    public String getIdentifier() {
        return identifier;
    }

    public UserLoginVO setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public String getCredential() {
        return credential;
    }

    public UserLoginVO setCredential(String credential) {
        this.credential = credential;
        return this;
    }
}