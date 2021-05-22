package com.sanmeng.app.domian.result;

import com.sanmeng.core.domain.ResultVo;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/5/22 3:05 下午
 * @Desc:
 */
@Data
public class TicketResultVo extends ResultVo {

    /**
     * ticket
     */
    private String ticket;

    /**
     * 过期时间
     */
    private Integer expires_in;

    /**
     * 签名
     */
    private String signature;

    /**
     * 随机串
     */
    private String nonceStr;

    /**
     * 时间戳
     */
    private Long timestamp;
}