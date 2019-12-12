package org.techas.falleaves.model;


import javax.persistence.*;

@Entity
@Table(name = "permission", schema = "falleaves", catalog = "")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String tag;

    public Long getId() {
        return id;
    }

    public PermissionEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PermissionEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public PermissionEntity setTag(String tag) {
        this.tag = tag;
        return this;
    }
}
