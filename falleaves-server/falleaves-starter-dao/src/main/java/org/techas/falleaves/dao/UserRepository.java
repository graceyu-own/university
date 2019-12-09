package org.techas.falleaves.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.techas.falleaves.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}