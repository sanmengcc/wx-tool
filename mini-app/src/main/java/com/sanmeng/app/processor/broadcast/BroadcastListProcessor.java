package com.sanmeng.app.processor.broadcast;

import com.alibaba.fastjson.JSONObject;
import com.sanmeng.core.processor.BaseProcessor;
import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.core.util.MyBeanUtil;
import com.sanmeng.app.constants.BroadcastConstants;
import com.sanmeng.app.domian.broadcast.ReqBroadcastListVo;
import com.sanmeng.app.domian.result.broadcast.BroadcastListResultVo;
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

    public static void main(String[] args) {
        ReqBroadcastListVo req = ReqBroadcastListVo.builder()
                .limit(10)
                .start(0)
                .build()
                .accessToken("45_QF7v6PCpEJHuM4H_UHSSpPr7VrLyCqTkzD28B1Pq55IxpCENQydZXCAk_bZhf49ooY4XZh2OyLUWIVkWzqhlM6Zqa7Ueq5iQ3l3OOiWWnLnEVyvxf4AdQctmUQyyn9j8ht7q-i0f6zWYKwkfCLHaAIATCY");
        BroadcastListResultVo result = BroadcastListProcessor.builder().build().executor(req);
        System.out.println(JSONObject.toJSONString(result));
    }
}