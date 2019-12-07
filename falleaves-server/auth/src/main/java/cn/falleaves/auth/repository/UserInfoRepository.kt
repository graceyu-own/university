package cn.falleaves.auth.repository

import cn.falleaves.auth.model.UserInfoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserInfoRepository: JpaRepository<UserInfoEntity, Long> {

}