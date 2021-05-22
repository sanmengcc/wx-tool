package com.sanmeng.app.domian.qr.req;

import com.sanmeng.core.domain.BaseValue;
import com.sanmeng.core.domain.Rgb;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/5/13 10:48 上午
 * @Desc:
 */
@Data
public class UnLimitQrReqVo extends BaseValue {

    private String scene;

    private String page;

    private String width;

    private boolean auto_color = false;

    private Rgb line_color = new Rgb();
}