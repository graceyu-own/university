package org.techas.falleaves.model;

import javax.persistence.*;

@Entity
@Table(name = "role_permission", schema = "falleaves", catalog = "")
public class RolePermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rid;
    private Long pid;


    public Long getId() {
        return id;
    }

    public RolePermissionEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRid() {
        return rid;
    }

    public RolePermissionEntity setRid(Long rid) {
        this.rid = rid;
        return this;
    }

    public Long getPid() {
        return pid;
    }

    public RolePermissionEntity setPid(Long pid) {
        this.pid = pid;
        return this;
    }
}
