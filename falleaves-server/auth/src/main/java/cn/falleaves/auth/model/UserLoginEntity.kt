package cn.falleaves.auth.model

import javax.persistence.*

@Entity
@Table(name = "user_login", schema = "falleaves", catalog = "")
open class UserLoginEntity {

    @get:Id
    @get:Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int? = null

    @get:Basic
    @get:Column(name = "uid", nullable = true)
    open var uid: Int? = null

    @get:Basic
    @get:Column(name = "types", nullable = false)
    open var types: Byte? = null

    @get:Basic
    @get:Column(name = "identifier", nullable = false)
    open var identifier: String? = null

    @get:Basic
    @get:Column(name = "credential", nullable = false)
    open var credential: String? = null

}

