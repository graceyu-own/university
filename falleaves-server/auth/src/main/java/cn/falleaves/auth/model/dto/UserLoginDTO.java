package cn.falleaves.auth.model.dto;

public class UserLoginDTO {

    private String identifier;
    private String credential;

    public UserLoginDTO(String identifier, String credential) {
        this.identifier = identifier;
        this.credential = credential;
    }

    public String getIdentifier() {
        return identifier;
    }

    public UserLoginDTO setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public String getCredential() {
        return credential;
    }

    public UserLoginDTO setCredential(String credential) {
        this.credential = credential;
        return this;
    }
}
