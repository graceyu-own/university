package cn.falleaves.common

class Utils {

    companion object {

        fun checkIdentifierType(identifier: String): IdentifierType {
            if (identifier.matches(Regex.EMAIL.pattern.toRegex())) {
                return IdentifierType.EMAIL
            }

            if (identifier.matches(Regex.PHONE.pattern.toRegex())) {
                return IdentifierType.PHONE
            }

            return IdentifierType.NICKNAME
        }
    }

}