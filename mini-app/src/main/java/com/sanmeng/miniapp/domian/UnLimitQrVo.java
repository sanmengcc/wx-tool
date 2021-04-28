package com.sanmeng.miniapp.domian;

import com.alibaba.fastjson.JSONObject;
import com.sanmeng.core.domain.BaseVo;
import com.sanmeng.core.domain.Rgb;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author：胡侯东
 * @Date：2021/4/27 5:22 下午
 * @Desc:
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UnLimitQrVo extends BaseVo {

    private String scene;

    private String page;

    private String width;

    private boolean auto_color;

    private Rgb line_color;
}