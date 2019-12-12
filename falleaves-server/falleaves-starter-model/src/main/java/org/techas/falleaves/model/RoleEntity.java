package org.techas.falleaves.model;


import javax.persistence.*;

@Entity
@Table(name = "role", schema = "falleaves", catalog = "")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String tag;


    public Long getId() {
        return id;
    }

    public RoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public RoleEntity setTag(String tag) {
        this.tag = tag;
        return this;
    }
}
