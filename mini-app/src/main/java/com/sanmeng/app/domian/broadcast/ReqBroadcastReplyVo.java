package com.sanmeng.app.domian.broadcast;

import com.sanmeng.core.domain.BaseVo;
import lombok.Builder;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/5/22 2:32 下午
 * @Desc:
 */
@Data
public class ReqBroadcastReplyVo extends BaseVo {

    /**
     * 获取回放
     */
    private String action = "get_replay";

    /**
     * 直播间ID
     */
    private Integer room_id;

    /**
     * 起始拉取视频，0表示从第一个视频片段开始拉取
     */
    private Integer start;

    /**
     * 每次拉取的数量，建议100以内
     */
    private Integer limit;

    @Builder
    public ReqBroadcastReplyVo(Integer room_id, Integer start, Integer limit) {
        this.action = "get_replay";
        this.room_id = room_id;
        this.start = start;
        this.limit = limit;
    }

    /**
     * 构建accessToken
     * @param accessToken
     * @return
     */
    public ReqBroadcastReplyVo accessToken(String accessToken) {
        super.setAccess_token(accessToken);
        return this;
    }

}