package com.sanmeng.app.processor.link;

import com.sanmeng.core.processor.BaseProcessor;
import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.core.util.MyBeanUtil;
import com.sanmeng.app.constants.LinkConstants;
import com.sanmeng.app.domian.link.LinkCloudBaseVo;
import com.sanmeng.app.domian.link.ReqLinkVo;
import com.sanmeng.app.domian.result.link.LinkDomainVo;
import lombok.Builder;

/**
 * 生成小程序跳转链接
 * @Author：胡侯东
 * @Date：2021/4/27 4:15 下午
 * @Desc: https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/url-link/urllink.generate.html
 */
@Builder
public class UnLinkProcessor extends BaseProcessor {

    public LinkDomainVo executor(ReqLinkVo baseVo) {
        super.before(baseVo);
        LinkDomainVo result = this.executor();
        super.after(result);
        return result;
    }

    @Override
    protected LinkDomainVo executor() {
        ReqLinkVo link = (ReqLinkVo) baseVo;
        LinkCloudBaseVo cloudBaseVo = new LinkCloudBaseVo();
        //link.setCloud_base(cloudBaseVo);
        String result = HttpUtil.payloadParam(super.getRequest(LinkConstants.URL_LINK_GENERATE), link);

        System.out.println(result);
        return MyBeanUtil.str2Bean(result, LinkDomainVo.class);
    }

    public static void main(String[] args) {
        ReqLinkVo req = ReqLinkVo.builder()
                .path("/chatPages/pages/chat-group/index")
                .query("1")
                .is_expire(false)
                .expire_type(1)
                .expire_time("1621574710000")
                .expire_interval(1)
                .build()
                .accessToken("45_tdCgR5Nzy8hwH5OEHsONZ5ca8GicjZdQWMPCrsTlLCR2Mc3mUiXnsmRtMBkG82keHWOb38Ck5aacBdLxDbC9NqhugaFNqciOCdfNBoq8VZsdkkWzpm7KaPezZU9kkePrzoKeXHYTqvc2zIovYEYhAGAGMG");
        LinkDomainVo domainVo = UnLinkProcessor.builder().build().executor(req);
        System.out.println(domainVo);

    }
}