package com.sanmeng.core.util;

import java.util.Collection;
import java.util.Map;

/**
 * @Author：胡侯东
 * @Date：2021/4/26 5:57 下午
 * @Desc:
 */

public class ValidateHelper {
    public ValidateHelper() {
    }

    public static boolean isEmptyString(String str) {
        return null == str || str.trim().length() == 0;
    }

    public static boolean isNotEmptyString(String str) {
        return !isEmptyString(str);
    }

    public static boolean isEmptyCollection(Collection<?> collection) {
        return null == collection || collection.size() == 0;
    }

    public static boolean isNotEmptyCollection(Collection<?> collection) {
        return !isEmptyCollection(collection);
    }

    public static boolean isEmptyMap(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    public static boolean isNotEmptyMap(Map<?, ?> map) {
        return !isEmptyMap(map);
    }
}