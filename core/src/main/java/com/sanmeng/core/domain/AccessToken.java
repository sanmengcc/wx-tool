package com.sanmeng.core.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/4/28 12:01 下午
 * @Desc:
 */
@Data
@Builder
public class AccessToken extends BaseValue{

    /**
     * 授权方式
     */
    @Builder.Default
    private String grant_type = "client_credential";

    /**
     * appid
     */
    private String appid;

    /**
     * 私钥
     */
    private String secret;

}