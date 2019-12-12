package org.techas.falleaves.model;

import javax.persistence.*;

@Entity
@Table(name = "user_role", schema = "falleaves", catalog = "")

public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;
    private Long rid;


    public Long getId() {
        return id;
    }

    public UserRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUid() {
        return uid;
    }

    public UserRoleEntity setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public Long getRid() {
        return rid;
    }

    public UserRoleEntity setRid(Long rid) {
        this.rid = rid;
        return this;
    }
}
