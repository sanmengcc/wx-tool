package com.sanmeng.app.domian.result.broadcast;

import com.sanmeng.core.domain.ResultVo;
import lombok.Data;
import java.util.List;

/**
 * @Author：胡侯东
 * @Date：2021/5/22 2:34 下午
 * @Desc:
 */
@Data
public class BroadcastReplyResultVo extends ResultVo {

    /**
     * 回放视频片段个数
     */
    private Integer total;

    /**
     * 片段
     */
    private List<BroadcastLiveReplyVo> live_replay;
}