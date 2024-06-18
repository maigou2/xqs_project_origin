package com.xqs.commoncore.util;

import java.util.Locale;
import java.util.UUID;

public class ObjectUtil {
    /**
     * 获取uuid.
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase(Locale.ROOT);
    }
}
