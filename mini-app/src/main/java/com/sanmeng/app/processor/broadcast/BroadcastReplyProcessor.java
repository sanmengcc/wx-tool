package com.sanmeng.app.processor.broadcast;

import com.alibaba.fastjson.JSONObject;
import com.sanmeng.core.processor.BaseProcessor;
import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.core.util.MyBeanUtil;
import com.sanmeng.app.constants.BroadcastConstants;
import com.sanmeng.app.domian.broadcast.ReqBroadcastReplyVo;
import com.sanmeng.app.domian.result.broadcast.BroadcastReplyResultVo;
import lombok.Builder;

/**
 * 获取直播回放
 * @Author：胡侯东
 * @Date：2021/4/27 4:15 下午
 * @Desc: https://developers.weixin.qq.com/miniprogram/dev/framework/liveplayer/studio-api.html#2
 */
@Builder
public class BroadcastReplyProcessor extends BaseProcessor {

    public BroadcastReplyResultVo executor(ReqBroadcastReplyVo baseVo) {
        super.before(baseVo);
        BroadcastReplyResultVo result = this.executor();
        super.after(result);
        return result;
    }

    @Override
    protected BroadcastReplyResultVo executor() {
        ReqBroadcastReplyVo req = (ReqBroadcastReplyVo) baseVo;
        String result = HttpUtil.payloadParam(super.getRequest(BroadcastConstants.GET_LIVE_REPLY_INFO), req);
        return MyBeanUtil.str2Bean(result, BroadcastReplyResultVo.class);
    }

    public static void main(String[] args) {
        ReqBroadcastReplyVo req = ReqBroadcastReplyVo.builder()
                .limit(100)
                .start(0)
                .room_id(75)
                .build()
                .accessToken("45_0PzxlftKDKN3DmLN0RsitfMr1FveTX9LZf05SRB4MB44jXi_l1EDh_vlzeeIt8xMkc8I-ZGit1zQaSfu6AR_u79coEsCK0uJjMm17Ydmepjb2o-WueJVBcqvE6AbfoxyzQHLo8ByklS_BlShHXIeAFADTE");
        BroadcastReplyResultVo result = BroadcastReplyProcessor.builder().build().executor(req);
        System.out.println(JSONObject.toJSONString(result));
    }
}