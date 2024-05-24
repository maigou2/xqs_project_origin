package com.xqs.commoncore.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum OperationTypeEnum {
    ADD(0, "新增"),
    UPDATE(1, "修改"),
    SELECT(2, "查询"),
    DELETE(3, "删除")
    ;

    private int type;
    private String displayName;

    OperationTypeEnum(int type, String displayName) {
        this.type = type;
        this.displayName = displayName;
    }

    public int getType() {
        return type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static OperationTypeEnum getByType(int type) {
        Optional<OperationTypeEnum> any = Arrays.stream(OperationTypeEnum.class.getEnumConstants())
                .filter(e -> e.getType() == type).findAny();
        if (any.isPresent()){
            return any.get();
        }
        return null;
    }

    public static List<OperationTypeEnum> getList() {
        return Arrays.asList(OperationTypeEnum.values());
    }

}
