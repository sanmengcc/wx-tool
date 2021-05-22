package com.sanmeng.app.domian.result.broadcast;

import com.sanmeng.core.domain.BaseValue;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/5/22 2:36 下午
 * @Desc:
 */
@Data
public class BroadcastLiveReplyVo extends BaseValue {

    /**
     * 回放视频url过期时间
     */
    private String expire_time;

    /**
     * 回放视频创建时间
     */
    private String create_time;

    /**
     * 回放视频链接
     */
    private String media_url;
}