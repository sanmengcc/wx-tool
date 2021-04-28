package com.sanmeng.core.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：胡侯东
 * @Date：2021/4/27 4:10 下午
 * @Desc:
 */
@Data
public class BaseVo implements Serializable {

    /**
     * 接口调用凭证
     */
    protected String access_token;
}