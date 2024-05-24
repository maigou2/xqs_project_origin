package com.xqs.commoncore.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum RedisDataTypeEnum {
    STRING(0, "字符串"),
    LIST(1, "列表"),
    SET(2, "集合"),
    S_SET(3, "有序集合"),
    HASH(4, "HASH")
    ;

    private int type;
    private String displayName;

    RedisDataTypeEnum(int type, String displayName) {
        this.type = type;
        this.displayName = displayName;
    }

    public int getType() {
        return type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static RedisDataTypeEnum getByType(int type) {
        Optional<RedisDataTypeEnum> any = Arrays.stream(RedisDataTypeEnum.class.getEnumConstants())
                .filter(e -> e.getType() == type).findAny();
        if (any.isPresent()){
            return any.get();
        }
        return null;
    }

    public static List<RedisDataTypeEnum> getList() {
        return Arrays.asList(RedisDataTypeEnum.values());
    }

}
