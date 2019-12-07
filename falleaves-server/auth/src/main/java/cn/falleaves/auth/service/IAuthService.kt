package cn.falleaves.auth.service

import cn.falleaves.auth.model.dto.UserLoginDTO
import cn.falleaves.auth.model.dto.UserRegisterDTO

interface IAuthService {

    fun login(userLoginDTO: UserLoginDTO): Boolean

    fun register(userRegisterDTO: UserRegisterDTO): Boolean

    fun logout(): Boolean
}