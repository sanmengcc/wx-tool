package com.sanmeng.core.util.encrypt;

import java.util.Formatter;

/**
 * @Author：胡侯东
 * @Date：2021/5/22 3:22 下午
 * @Desc:
 */
public class HexUtil {

    /**
     * byte2Hex
     * @param hash
     * @return
     */
    public static String byte2Hex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}