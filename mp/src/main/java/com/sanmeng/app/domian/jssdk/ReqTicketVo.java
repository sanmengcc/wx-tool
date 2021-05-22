package com.sanmeng.app.domian.jssdk;

import com.sanmeng.core.domain.BaseVo;
import lombok.Builder;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/5/22 3:04 下午
 * @Desc:
 */
@Data
public class ReqTicketVo extends BaseVo {

    private String type;

    private String url;

    @Builder
    public ReqTicketVo(String url) {
        this.type = "jsapi";
        this.url = url;
    }

    /**
     * 构建accessToken
     * @param accessToken
     * @return
     */
    public ReqTicketVo accessToken(String accessToken) {
        super.setAccess_token(accessToken);
        return this;
    }

}