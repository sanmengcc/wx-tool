package com.sanmeng.miniapp.processor.link;

import com.sanmeng.core.processor.BaseProcessor;
import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.core.util.MyBeanUtil;
import com.sanmeng.miniapp.constants.LinkConstants;
import com.sanmeng.miniapp.domian.link.ReqLinkVo;
import com.sanmeng.miniapp.domian.result.link.LinkDomainVo;
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
        String result = HttpUtil.payloadParam(super.getRequest(LinkConstants.URL_LINK_GENERATE), link);

        System.out.println(result);
        return MyBeanUtil.str2Bean(result, LinkDomainVo.class);
    }
}