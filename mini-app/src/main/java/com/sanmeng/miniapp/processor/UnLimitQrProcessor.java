package com.sanmeng.miniapp.processor;

import com.alibaba.fastjson.JSONObject;
import com.sanmeng.core.domain.BaseVo;
import com.sanmeng.core.domain.ResultVo;
import com.sanmeng.core.domain.Rgb;
import com.sanmeng.core.processor.BaseProcessor;
import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.miniapp.domian.UnLimitQrVo;

/**
 * @Author：胡侯东
 * @Date：2021/4/27 4:15 下午
 * @Desc:
 */
public class UnLimitQrProcessor extends BaseProcessor {

    public static UnLimitQrProcessor getInstance(BaseVo baseVo) {
        return new UnLimitQrProcessor(baseVo);
    }

    public UnLimitQrProcessor(BaseVo baseVo) {
        super.baseVo = baseVo;
    }

    @Override
    protected ResultVo executor() {
        UnLimitQrVo unLimitQrVo = new UnLimitQrVo();
        unLimitQrVo.setPage("pages/welcome/welcome");
        unLimitQrVo.setScene("scene");
        unLimitQrVo.setWidth("420");
        unLimitQrVo.setAuto_color(false);
        unLimitQrVo.setLine_color(new Rgb());
        System.out.println(JSONObject.toJSONString(unLimitQrVo));
        byte[] bytes = HttpUtil.payload("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + baseVo.getAccess_token(), JSONObject.toJSONString(unLimitQrVo));
        return null;
    }
}