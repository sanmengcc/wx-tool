package com.sanmeng.app.domian.qr;

import com.sanmeng.core.domain.BaseVo;
import com.sanmeng.core.domain.Rgb;
import com.sanmeng.core.domain.font.QrFontVo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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

    private boolean auto_color = false;

    private Rgb line_color = new Rgb();

    /**
     * 需要替换的logo
     */
    private byte[] mergeImage;

    /**
     * 需要替换的logo url
     */
    private String mergeImageUrl;

    /**
     * 填充的文字
     */
    private List<QrFontVo> fonts;

    @Builder
    public UnLimitQrMergeVo(String mergeImageUrl,List<QrFontVo> fonts,String scene, String page, String width, boolean auto_color, Rgb line_color, byte[] mergeImage,String accessToken) {
        super.setAccess_token(accessToken);
        this.fonts = fonts;
        this.mergeImageUrl = mergeImageUrl;
        this.scene = scene;
        this.page = page;
        this.width = width;
        this.auto_color = auto_color;
        this.line_color = line_color;
        this.mergeImage = mergeImage;
    }
}