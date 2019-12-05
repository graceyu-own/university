package cn.falleaves.auth.repository;

import cn.falleaves.auth.model.UserLogin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {


}
