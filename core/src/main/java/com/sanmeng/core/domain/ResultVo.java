package com.sanmeng.core.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：胡侯东
 * @Date：2021/4/27 4:11 下午
 * @Desc:
 */
@Data
public class ResultVo implements Serializable {

    /**
     * 错误码
     * 0	请求成功
     * -1	系统繁忙，此时请开发者稍候再试
     */
    private Integer errcode = 0;

    /**
     * 错误信息
     */
    private String errmsg = "success";

}