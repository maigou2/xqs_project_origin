package com.xqs.commoncore.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum FourOperationsTypeEnum {
    ADD(0, "加法"),
    SUBTRACT(1, "减法"),
    MULTIPLY(2, "乘法"),
    DIVIDE(3, "除法")
    ;

    private int type;
    private String displayName;

    FourOperationsTypeEnum(int type, String displayName) {
        this.type = type;
        this.displayName = displayName;
    }

    public int getType() {
        return type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static FourOperationsTypeEnum getByType(int type) {
        Optional<FourOperationsTypeEnum> any = Arrays.stream(FourOperationsTypeEnum.class.getEnumConstants())
                .filter(e -> e.getType() == type).findAny();
        if (any.isPresent()){
            return any.get();
        }
        return null;
    }

    public static List<FourOperationsTypeEnum> getList() {
        return Arrays.asList(FourOperationsTypeEnum.values());
    }

}
