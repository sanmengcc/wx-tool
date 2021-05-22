package com.sanmeng.app.domian.broadcast;

import com.sanmeng.core.domain.BaseVo;
import lombok.Builder;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/5/22 1:38 下午
 * @Desc:
 */
@Data
public class ReqBroadcastListOneVo extends BaseVo {

    /**
     * 房间ID
     */
    private Integer roomid;

    /**
     * 起始房间，0表示从第1个房间开始拉取
     */
    private Integer start;

    /**
     * 每次拉取的房间数量，建议100以内
     */
    private Integer limit;

    @Builder
    public ReqBroadcastListOneVo(Integer roomid, Integer start, Integer limit) {
        this.roomid = roomid;
        this.start = start;
        this.limit = limit;
    }

    /**
     * 构建accessToken
     * @param accessToken
     * @return
     */
    public ReqBroadcastListOneVo accessToken(String accessToken) {
        super.setAccess_token(accessToken);
        return this;
    }

}