package com.sanmeng.app.processor;

import com.sanmeng.app.constants.Oauth2Constants;
import com.sanmeng.app.domian.oauth2.ReqOauth2Vo;
import com.sanmeng.app.domian.result.oauth2.Oauth2ResultVo;
import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.core.util.MyBeanUtil;
import lombok.Builder;


/**
 * 微信应用oauth2登陆
 * @Author：胡侯东
 * @Date：2021/4/27 4:15 下午
 * @Desc: https://developers.weixin.qq.com/doc/oplatform/Mobile_App/WeChat_Login/Development_Guide.html
 */
@Builder
public class Oauth2Processor {

    private ReqOauth2Vo baseVo;


    public Oauth2ResultVo executor(ReqOauth2Vo baseVo) {
        this.baseVo = baseVo;
        return this.executor();
    }

    protected Oauth2ResultVo executor() {
        String result = HttpUtil.get(Oauth2Constants.OAUTH2_CODE_LOGIN, MyBeanUtil.bean2Map(baseVo));
        return MyBeanUtil.str2Bean(result, Oauth2ResultVo.class);
    }
}