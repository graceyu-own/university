package cn.falleaves.auth.model;


import javax.persistence.*;

@Entity
@Table(name = "user_login")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long uid;
    private Integer types;
    private String identifier;
    private String credential;

    public Long getId() {
        return id;
    }

    public UserLogin setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUid() {
        return uid;
    }

    public UserLogin setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public Integer getTypes() {
        return types;
    }

    public UserLogin setTypes(Integer types) {
        this.types = types;
        return this;
    }

    public String getIdentifier() {
        return identifier;
    }

    public UserLogin setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public String getCredential() {
        return credential;
    }

    public UserLogin setCredential(String credential) {
        this.credential = credential;
        return this;
    }
}
