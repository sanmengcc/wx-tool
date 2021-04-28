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
public class UnLimitQrVo extends BaseVo {

    private String scene;

    private String page;

    private String width;

    private boolean auto_color;

    private Rgb line_color;

    @Builder
    public UnLimitQrVo(String scene, String page, String width, boolean auto_color, Rgb line_color,String accessToken) {
        this.scene = scene;
        this.page = page;
        this.width = width;
        this.auto_color = auto_color;
        this.line_color = line_color;
        super.setAccess_token(accessToken);
    }
}