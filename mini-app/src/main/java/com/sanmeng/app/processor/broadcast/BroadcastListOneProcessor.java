package com.sanmeng.app.processor.broadcast;

import com.alibaba.fastjson.JSONObject;
import com.sanmeng.core.processor.BaseProcessor;
import com.sanmeng.core.util.MyBeanUtil;
import com.sanmeng.core.util.ValidateHelper;
import com.sanmeng.app.domian.broadcast.ReqBroadcastListOneVo;
import com.sanmeng.app.domian.broadcast.ReqBroadcastListVo;
import com.sanmeng.app.domian.result.broadcast.BroadcastListOneResultVo;
import com.sanmeng.app.domian.result.broadcast.BroadcastListResultVo;
import com.sanmeng.app.domian.result.broadcast.BroadcastListVo;
import lombok.Builder;

import java.util.List;
import java.util.Optional;

/**
 * 递归获取直播间列表并筛选出roomid一致的直播间
 * @Author：胡侯东
 * @Date：2021/4/27 4:15 下午
 * @Desc: https://developers.weixin.qq.com/miniprogram/dev/framework/liveplayer/studio-api.html#2
 */
@Builder
public class BroadcastListOneProcessor extends BaseProcessor {

    public BroadcastListOneResultVo executor(ReqBroadcastListOneVo baseVo) {
        super.before(baseVo);
        BroadcastListOneResultVo result = this.executor();
        super.after(result);
        return result;
    }

    @Override
    protected BroadcastListOneResultVo executor() {
        ReqBroadcastListOneVo reqOne = (ReqBroadcastListOneVo) baseVo;
        ReqBroadcastListVo req = MyBeanUtil.createBean(reqOne, ReqBroadcastListVo.class);
        req.setAccess_token(reqOne.getAccess_token());
        BroadcastListResultVo resultVo = BroadcastRecursiveListProcessor
                .builder()
                .build()
                .executor(req);


        List<BroadcastListVo> broadcastListVos = resultVo.getRoom_info();
        if (ValidateHelper.isNotEmptyCollection(broadcastListVos)) {
            Optional<BroadcastListVo> optional = broadcastListVos.stream()
                    .filter(b -> b.getRoomid().equals(reqOne.getRoomid()))
                    .findAny();
            if (optional.isPresent()) {
                return MyBeanUtil.createBean(optional.get(), BroadcastListOneResultVo.class);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ReqBroadcastListOneVo req = ReqBroadcastListOneVo.builder()
                .limit(100)
                .start(0)
                .roomid(37)
                .build()
                .accessToken("45_QF7v6PCpEJHuM4H_UHSSpPr7VrLyCqTkzD28B1Pq55IxpCENQydZXCAk_bZhf49ooY4XZh2OyLUWIVkWzqhlM6Zqa7Ueq5iQ3l3OOiWWnLnEVyvxf4AdQctmUQyyn9j8ht7q-i0f6zWYKwkfCLHaAIATCY");
        BroadcastListOneResultVo result = BroadcastListOneProcessor.builder().build().executor(req);
        System.out.println(JSONObject.toJSONString(result));
    }
}