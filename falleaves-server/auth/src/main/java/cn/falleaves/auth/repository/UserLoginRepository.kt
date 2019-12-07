package cn.falleaves.auth.repository

import cn.falleaves.auth.model.UserLoginEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserLoginRepository: JpaRepository<UserLoginEntity, Long> {

}