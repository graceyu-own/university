package org.techas.falleaves.utils;

public enum UserStatusType {

    NORMAL(1),
    LOCKED(2),
    DISABLED(3)

    ;

    private int statusType;

    UserStatusType(int statusType) {
        this.statusType = statusType;
    }

    public int getStatusType() {
        return statusType;
    }

    public static boolean has(int statusType) {
        for (UserStatusType value : UserStatusType.values()) {
            if(value.statusType == statusType) {
                return true;
            }
        }
        return false;
    }
}