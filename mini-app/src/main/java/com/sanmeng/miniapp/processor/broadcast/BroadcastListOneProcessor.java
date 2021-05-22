package com.sanmeng.miniapp.processor.broadcast;

import com.sanmeng.core.processor.BaseProcessor;
import com.sanmeng.core.util.MyBeanUtil;
import com.sanmeng.core.util.ValidateHelper;
import com.sanmeng.miniapp.domian.broadcast.ReqBroadcastListOneVo;
import com.sanmeng.miniapp.domian.broadcast.ReqBroadcastListVo;
import com.sanmeng.miniapp.domian.result.broadcast.BroadcastListOneResultVo;
import com.sanmeng.miniapp.domian.result.broadcast.BroadcastListResultVo;
import com.sanmeng.miniapp.domian.result.broadcast.BroadcastListVo;
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
}