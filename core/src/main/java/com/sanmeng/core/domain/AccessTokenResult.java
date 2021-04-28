package com.sanmeng.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/4/28 12:03 下午
 * @Desc:
 */
@Data
public class AccessTokenResult extends ResultVo{

    /**
     * 接口凭证
     */
    private String access_token;

    /**
     * 剩余失效时间
     */
    private Integer expires_in;

}