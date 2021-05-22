package com.sanmeng.core.domain.font;

import com.sanmeng.core.domain.BaseValue;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/5/13 9:39 上午
 * @Desc:
 */
@Data
public class QrFontVo extends BaseValue {

    /**
     * 标题
     */
    private String title;

    /**
     * x偏移量
     */
    private Integer x = 0;

    /**
     * y偏移量
     */
    private Integer y = 0;
}