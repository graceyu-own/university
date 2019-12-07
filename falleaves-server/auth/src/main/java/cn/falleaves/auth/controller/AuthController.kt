package cn.falleaves.auth.controller

import cn.falleaves.auth.model.dto.UserLoginDTO
import cn.falleaves.auth.model.vo.UserLoginVO
import cn.falleaves.auth.model.vo.UserRegisterVO
import cn.falleaves.auth.service.impl.AuthService
import cn.falleaves.common.ResponseData
import cn.falleaves.common.ReturnValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    private var authService: AuthService? = null

    @RequestMapping("/login")
    fun login(userLoginVO: UserLoginVO): ResponseData<String> {

        if (null == userLoginVO.identifier || userLoginVO.identifier.equals("")) {
            val rd: ResponseData<String> = ResponseData.new(ReturnValue.IDENTIFIER_EMPTY)
            println(rd.code)
            return rd
        }

        if (null == userLoginVO.credential || userLoginVO.credential.equals("")) {
            return ResponseData.new(ReturnValue.CREDENTIAL_EMPTY)
        }

        return try {
            val result = authService!!.login(UserLoginDTO(userLoginVO.identifier, userLoginVO.credential))
            if (!result) {
                ResponseData.new(ReturnValue.INCORRECT_CREDENTIALS)
            } else ResponseData.new(ReturnValue.SUCCESS)
        } catch (e: Exception) {
            ResponseData.new(ReturnValue.INTERNET_SERVER_ERROR)
        }
    }

    @RequestMapping("/register")
    fun register(userRegisterVO: UserRegisterVO): ResponseData<String> {
        return ResponseData(0.0, "null")
    }

    @RequestMapping("/logout")
    fun logout(): ResponseData<String> {
        return ResponseData(0.0, "null")
    }

}