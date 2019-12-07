package cn.falleaves.auth.service.impl

import cn.falleaves.auth.model.UserInfoEntity
import cn.falleaves.auth.model.UserLoginEntity
import cn.falleaves.auth.model.dto.UserLoginDTO
import cn.falleaves.auth.model.dto.UserRegisterDTO
import cn.falleaves.auth.repository.UserInfoRepository
import cn.falleaves.auth.repository.UserLoginRepository
import cn.falleaves.auth.service.IAuthService
import cn.falleaves.common.IdentifierType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

@Service
class AuthService: IAuthService {

    @Autowired
    private var userLoginRepository: UserLoginRepository ?= null

    @Autowired
    private var userInfoRepository: UserInfoRepository ?= null

    override fun login(userLoginDTO: UserLoginDTO): Boolean {

        val userLogin = UserLoginEntity()
            userLogin.identifier = userLoginDTO.identifier
            userLogin.credential = userLoginDTO.credential

        val one = userLoginRepository?.findOne(Example.of(userLogin))

        return one?.isPresent!!
    }

    override fun register(userRegisterDTO: UserRegisterDTO): Boolean {

        var userInfo = UserInfoEntity()
            userInfo.nickname = userRegisterDTO.nickname
            userInfo.email    = userRegisterDTO.email

        userInfo = userInfoRepository?.save(userInfo)!!

        val userLogin = UserLoginEntity()
            userLogin.uid   = userInfo.id
            userLogin.types = IdentifierType.NICKNAME.typeId
            userLogin.identifier = userRegisterDTO.nickname
            userLogin.credential = userRegisterDTO.password

        userLoginRepository?.save(userLogin)

        userLogin.types = IdentifierType.EMAIL.typeId
        userLogin.identifier = userRegisterDTO.email

        userLoginRepository?.save(userLogin)
        userLoginRepository?.flush()

        return false
    }

    override fun logout(): Boolean {
        return false
    }
}