package com.sanmeng.mp.processor;

import com.sanmeng.core.processor.BaseProcessor;
import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.core.util.MyBeanUtil;
import com.sanmeng.core.util.encrypt.HexUtil;
import com.sanmeng.mp.constants.JSSDKConstants;
import com.sanmeng.mp.domian.jssdk.ReqTicketVo;
import com.sanmeng.mp.domian.result.TicketResultVo;
import lombok.Builder;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * 获取JSSDK的ticket
 * @Author：胡侯东
 * @Date：2021/4/27 4:15 下午
 * @Desc: https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html
 */
@Builder
public class TicketProcessor extends BaseProcessor {

    public TicketResultVo executor(ReqTicketVo baseVo) {
        super.before(baseVo);
        TicketResultVo result = this.executor();
        super.after(result);
        return result;
    }

    @Override
    protected TicketResultVo executor() {
        ReqTicketVo req = (ReqTicketVo) baseVo;
        String result = HttpUtil.get(super.getRequest(JSSDKConstants.GET_TICKET), MyBeanUtil.bean2Map(req));
        TicketResultVo resultVo = MyBeanUtil.str2Bean(result, TicketResultVo.class);
        long timestamp = System.currentTimeMillis() / 1000;
        String nonceStr = UUID.randomUUID().toString();
        String param = "jsapi_ticket=" + resultVo.getTicket()
                + "&noncestr=" + nonceStr
                + "&timestamp=" + timestamp
                + "&url=" + req.getUrl();
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(param.getBytes());
            resultVo.setSignature(HexUtil.byte2Hex(crypt.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultVo.setNonceStr(nonceStr);
        resultVo.setTimestamp(timestamp);
        return resultVo;
    }
}