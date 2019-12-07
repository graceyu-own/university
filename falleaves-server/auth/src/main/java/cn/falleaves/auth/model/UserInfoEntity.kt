package cn.falleaves.auth.model

import javax.persistence.*

@Entity
@Table(name = "user_info", schema = "falleaves", catalog = "")
open class UserInfoEntity {

    @get:Id
    @get:Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int? = null

    @get:Basic
    @get:Column(name = "nickname", nullable = true)
    open var nickname: String? = null

    @get:Basic
    @get:Column(name = "email", nullable = true)
    open var email: String? = null

    @get:Basic
    @get:Column(name = "phone", nullable = true)
    open var phone: String? = null

    @get:Basic
    @get:Column(name = "age", nullable = true)
    open var age: Byte? = null

    @get:Basic
    @get:Column(name = "location", nullable = true)
    open var location: String? = null

    @get:Basic
    @get:Column(name = "birthday", nullable = true)
    open var birthday: java.sql.Timestamp? = null

    @get:Basic
    @get:Column(name = "createAt", nullable = true)
    open var createAt: java.sql.Timestamp? = null

    @get:Basic
    @get:Column(name = "updateAt", nullable = true)
    open var updateAt: java.sql.Timestamp? = null
}

