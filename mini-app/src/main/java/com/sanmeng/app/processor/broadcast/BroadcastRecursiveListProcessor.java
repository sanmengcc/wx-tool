package com.sanmeng.app.processor.broadcast;

import com.alibaba.fastjson.JSONObject;
import com.sanmeng.core.processor.BaseProcessor;
import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.core.util.MyBeanUtil;
import com.sanmeng.app.constants.BroadcastConstants;
import com.sanmeng.app.domian.broadcast.ReqBroadcastListVo;
import com.sanmeng.app.domian.result.broadcast.BroadcastListResultVo;
import com.sanmeng.app.domian.result.broadcast.BroadcastListVo;
import lombok.Builder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 递归获取直播间列表
 * @Author：胡侯东
 * @Date：2021/4/27 4:15 下午
 * @Desc: https://developers.weixin.qq.com/miniprogram/dev/framework/liveplayer/studio-api.html#2
 */
@Builder
public class BroadcastRecursiveListProcessor extends BaseProcessor {

    public BroadcastListResultVo executor(ReqBroadcastListVo baseVo) {
        super.before(baseVo);
        BroadcastListResultVo result = this.executor();
        super.after(result);
        return result;
    }

    @Override
    protected BroadcastListResultVo executor() {
        ReqBroadcastListVo req = (ReqBroadcastListVo) baseVo;
        List<BroadcastListVo> broadcastListVos = new ArrayList<>();
        AtomicInteger start = new AtomicInteger(req.getStart());
        while (start.get() >= 0) {
            recursive(req, start,broadcastListVos);
        }
        BroadcastListResultVo result = new BroadcastListResultVo();
        result.setRoom_info(broadcastListVos);
        result.setTotal(broadcastListVos.size());
        return result;
    }

    /**
     * 获取直播列表
     *
     * @param req
     * @return
     */
    public void recursive(ReqBroadcastListVo req, AtomicInteger start,List<BroadcastListVo> broadcastVos) {
        String result = HttpUtil.payloadParam(super.getRequest(BroadcastConstants.GET_BROADCAST_LIST), req);
        BroadcastListResultVo resultVo = MyBeanUtil.str2Bean(result, BroadcastListResultVo.class);
        Integer index = req.getStart() == 0 ? 1 : req.getStart();
        Integer count = req.getLimit() + index;
        if (resultVo.getTotal() > count) {
            start.set(count);
        }else{
            start.set(-1);
        }
        req.setStart(req.getStart() + req.getLimit());
        broadcastVos.addAll(resultVo.getRoom_info());
    }

    public static void main(String[] args) {
        ReqBroadcastListVo req = ReqBroadcastListVo.builder()
                .limit(10)
                .start(0)
                .build()
                .accessToken("45_QF7v6PCpEJHuM4H_UHSSpPr7VrLyCqTkzD28B1Pq55IxpCENQydZXCAk_bZhf49ooY4XZh2OyLUWIVkWzqhlM6Zqa7Ueq5iQ3l3OOiWWnLnEVyvxf4AdQctmUQyyn9j8ht7q-i0f6zWYKwkfCLHaAIATCY");
        BroadcastListResultVo result = BroadcastRecursiveListProcessor.builder().build().executor(req);
        System.out.println(JSONObject.toJSONString(result));
    }
}