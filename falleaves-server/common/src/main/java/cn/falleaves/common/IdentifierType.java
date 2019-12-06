package cn.falleaves.common;

/**
 * 登录标识符可用类型
 *
 * @author graceyu
 * @since 1.8
 */
public enum IdentifierType {

    NICKNAME(1),
    EMAIL(2),
    PHONE(3),
    ;

    private int typeId;

    IdentifierType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
}
