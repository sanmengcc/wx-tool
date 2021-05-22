package com.sanmeng.app.processor.qr;

import com.alibaba.fastjson.JSONObject;
import com.sanmeng.core.processor.BaseProcessor;
import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.app.constants.QrConstants;
import com.sanmeng.app.domian.qr.UnLimitQrVo;
import com.sanmeng.app.domian.result.StreamDomainVo;
import lombok.Builder;

/**
 * 生成无限的小程序二维码
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