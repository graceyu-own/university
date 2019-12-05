package cn.falleaves.auth.utils;

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
