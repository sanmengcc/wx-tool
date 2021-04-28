package com.sanmeng.miniapp.processor;

import com.alibaba.fastjson.JSONObject;
import com.sanmeng.core.processor.BaseProcessor;
import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.miniapp.constants.QrConstants;
import com.sanmeng.miniapp.domian.qr.UnLimitQrVo;
import com.sanmeng.miniapp.domian.result.StreamDomainVo;
import lombok.Builder;

/**
 * @Author：胡侯东
 * @Date：2021/4/27 4:15 下午
 * @Desc: https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.getUnlimited.html
 */
@Builder
public class UnLimitQrProcessor extends BaseProcessor {

    public StreamDomainVo executor(UnLimitQrVo baseVo) {
        super.before(baseVo);
        StreamDomainVo result = this.executor();
        super.after(result);
        return result;
    }

    @Override
    protected StreamDomainVo executor() {
        UnLimitQrVo unLimitQrVo = (UnLimitQrVo) baseVo;
        byte[] bytes = HttpUtil.payload(super.getRequest(QrConstants.UN_LIMIT), JSONObject.toJSONString(unLimitQrVo));
        return StreamDomainVo.builder().data(bytes).build();
    }
}