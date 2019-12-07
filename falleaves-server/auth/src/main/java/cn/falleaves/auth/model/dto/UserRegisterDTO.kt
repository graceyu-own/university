package cn.falleaves.auth.model.dto

class UserRegisterDTO(nickname: String ?= null, email: String ?= null, password: String ?= null) {

    var nickname: String? = null
    var email: String? = null
    var password: String? = null
}