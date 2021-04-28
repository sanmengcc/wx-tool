package com.sanmeng.miniapp.domian.qr;

import com.sanmeng.core.domain.BaseVo;
import com.sanmeng.core.domain.Rgb;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/4/27 5:22 下午
 * @Desc:
 */
@Data
public class UnLimitQrMergeVo extends BaseVo {

    private String scene;

    private String page;

    private String width;

    private boolean auto_color;

    private Rgb line_color;

    /**
     * 需要替换的logo
     */
    private byte[] mergeImage;
}