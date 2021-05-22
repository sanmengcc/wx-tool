package com.sanmeng.miniapp.domian.broadcast;

import com.sanmeng.core.domain.BaseVo;
import lombok.Builder;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/5/22 1:38 下午
 * @Desc:
 */
@Data
public class ReqBroadcastListVo extends BaseVo {

    /**
     * 起始房间，0表示从第1个房间开始拉取
     */
    private Integer start;

    /**
     * 每次拉取的房间数量，建议100以内
     */
    private Integer limit;

    @Builder
    public ReqBroadcastListVo(Integer start, Integer limit) {
        this.start = start;
        this.limit = limit;
    }

    /**
     * 构建accessToken
     * @param accessToken
     * @return
     */
    public ReqBroadcastListVo accessToken(String accessToken) {
        super.setAccess_token(accessToken);
        return this;
    }

}