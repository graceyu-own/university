package org.techas.falleaves.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user", schema = "falleaves", catalog = "")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer status;

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UserEntity setStatus(Integer status) {
        this.status = status;
        return this;
    }

}

