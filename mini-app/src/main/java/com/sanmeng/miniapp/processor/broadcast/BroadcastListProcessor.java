package com.sanmeng.miniapp.processor.broadcast;

import com.sanmeng.core.processor.BaseProcessor;
import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.core.util.MyBeanUtil;
import com.sanmeng.miniapp.constants.BroadcastConstants;
import com.sanmeng.miniapp.domian.broadcast.ReqBroadcastListVo;
import com.sanmeng.miniapp.domian.result.broadcast.BroadcastListResultVo;
import lombok.Builder;

/**
 * 获取直播间列表
 * @Author：胡侯东
 * @Date：2021/4/27 4:15 下午
 * @Desc: https://developers.weixin.qq.com/miniprogram/dev/framework/liveplayer/studio-api.html#2
 */
@Builder
public class BroadcastListProcessor extends BaseProcessor {

    public BroadcastListResultVo executor(ReqBroadcastListVo baseVo) {
        super.before(baseVo);
        BroadcastListResultVo result = this.executor();
        super.after(result);
        return result;
    }

    @Override
    protected BroadcastListResultVo executor() {
        ReqBroadcastListVo req = (ReqBroadcastListVo) baseVo;
        String result = HttpUtil.payloadParam(super.getRequest(BroadcastConstants.GET_BROADCAST_LIST), req);
        return MyBeanUtil.str2Bean(result, BroadcastListResultVo.class);
    }

}