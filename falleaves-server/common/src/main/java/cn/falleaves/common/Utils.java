package cn.falleaves.common;

public class Utils {

    public static IdentifierType checkIdentifierType(String identifier) {

        if (identifier.matches(Regex.EMAIL.getPattern())) {
            return IdentifierType.EMAIL;
        } else

        if (identifier.matches(Regex.PHONE.getPattern())) {
            return IdentifierType.PHONE;
        }

        return IdentifierType.NICKNAME;
    }
}
