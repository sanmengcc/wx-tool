package com.sanmeng.core.util.font;

import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.core.util.file.FileUtil;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author：胡侯东
 * @Date：2021/5/13 9:44 上午
 * @Desc:
 */
public class FontUtil {

    private static String FONT = "FONT";

    private static Map<String, byte[]> catchMap = new HashMap<>();

    /**
     * 获取字体
     * @param filepath
     * @return
     */
    public static java.awt.Font getFont(String filepath){
        java.awt.Font font = null;
        try{
            byte[] bytes = catchMap.get(FONT);
            if (bytes == null || bytes.length <= 0) {
                bytes = HttpUtil.getPayload(filepath);;
                catchMap.put(FONT, bytes);
            }
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            BufferedInputStream fb = new BufferedInputStream(inputStream);
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, fb);
            font = font.deriveFont(Font.BOLD, 20);
        }catch (FontFormatException e){
            e.printStackTrace();
            return null;
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return font;
    }
}