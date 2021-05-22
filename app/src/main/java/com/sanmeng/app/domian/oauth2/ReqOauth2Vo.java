package com.sanmeng.app.domian.oauth2;

import com.sanmeng.core.domain.BaseValue;
import lombok.Builder;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/5/22 3:30 下午
 * @Desc:
 */
@Data
public class ReqOauth2Vo extends BaseValue {

    /**
     * appid
     */
    private String appid;

    /**
     * 请求私钥
     */
    private String secret;

    /**
     * 解密code
     */
    private String code;

    /**
     * 授权类型
     */
    private String grant_type;

    @Builder
    public ReqOauth2Vo(String appid, String secret, String code) {
        this.appid = appid;
        this.secret = secret;
        this.code = code;
        this.grant_type = "authorization_code";
    }
}