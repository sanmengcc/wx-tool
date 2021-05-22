package com.sanmeng.app.domian.result.broadcast;

import com.sanmeng.core.domain.ResultVo;
import lombok.Data;

import java.util.List;

/**
 * @Author：胡侯东
 * @Date：2021/5/22 1:36 下午
 * @Desc:
 */
@Data
public class BroadcastListResultVo extends ResultVo {

    /**
     * 直播间信息
     */
    private List<BroadcastListVo> room_info;

    /**
     * 直播间数量
     */
    private Integer total;


}