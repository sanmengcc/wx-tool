package com.sanmeng.core.util;

import com.alibaba.fastjson.JSON;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class MyBeanUtil extends BeanUtils {

    public static <T> T createBean(Object origObj, Class<T> destClazz) {
        String jsonStr = JSON.toJSONString(origObj);
        return JSON.parseObject(jsonStr, destClazz);
    }

    public static <T> List<T> createList(List<?> origList, Class<T> destClazz) {
        String jsonStr = JSON.toJSONString(origList);
        return JSON.parseArray(jsonStr, destClazz);
    }

    public static <T> T str2Bean(String jsonStr, Class<T> destClazz) {
        return JSON.parseObject(jsonStr, destClazz);
    }

    public static <T> List<T> str2List(String jsonStr, Class<T> destClazz) {
        return JSON.parseArray(jsonStr, destClazz);
    }

    public static Map bean2Map(Object obj) {
        String jsonStr = JSON.toJSONString(obj);
        return (Map)JSON.parse(jsonStr);
    }

    public static <T> T map2Bean(Map map, Class<T> destClazz) {
        String jsonStr = JSON.toJSONString(map);
        return JSON.parseObject(jsonStr, destClazz);
    }

    public static void copyBeanNotNull2Bean(Object databean, Object tobean) {
        PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(databean);

        for(int i = 0; i < origDescriptors.length; ++i) {
            String name = origDescriptors[i].getName();
            if (!"class".equals(name) && PropertyUtils.isReadable(databean, name) && PropertyUtils.isWriteable(tobean, name)) {
                try {
                    Object value = PropertyUtils.getSimpleProperty(databean, name);
                    if (value != null) {
                        copyProperty(tobean, name, value);
                    }
                } catch (IllegalArgumentException var6) {
                } catch (Exception var7) {
                }
            }
        }

    }
}
