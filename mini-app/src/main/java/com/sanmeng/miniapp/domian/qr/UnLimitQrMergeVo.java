package com.sanmeng.miniapp.domian.qr;

import com.sanmeng.core.domain.BaseVo;
import com.sanmeng.core.domain.Rgb;
import lombok.Builder;
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

    @Builder
    public UnLimitQrMergeVo(String scene, String page, String width, boolean auto_color, Rgb line_color, byte[] mergeImage,String accessToken) {
        this.scene = scene;
        this.page = page;
        this.width = width;
        this.auto_color = auto_color;
        this.line_color = line_color;
        this.mergeImage = mergeImage;
    }
}