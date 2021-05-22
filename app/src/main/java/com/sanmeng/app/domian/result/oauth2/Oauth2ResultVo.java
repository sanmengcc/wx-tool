package com.sanmeng.app.domian.result.oauth2;

import com.sanmeng.core.domain.ResultVo;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/5/22 3:33 下午
 * @Desc:
 */
@Data
public class Oauth2ResultVo extends ResultVo {

    /**
     * 接口调用凭证
     */
    private String access_token;

    /**
     * access_token 接口调用凭证超时时间，单位（秒）
     */
    private String expires_in;

    /**
     * 用户刷新 access_token）
     */
    private String refresh_token;

    /**
     * 授权用户唯一标识
     */
    private String openid;

    /**
     * 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的 unionid 是唯一的。
     */
    private String unionid;

    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;
}