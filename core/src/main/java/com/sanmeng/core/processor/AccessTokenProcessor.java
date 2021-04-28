package com.sanmeng.core.processor;

import com.sanmeng.core.constants.TokenConstants;
import com.sanmeng.core.domain.AccessToken;
import com.sanmeng.core.domain.AccessTokenResult;
import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.core.util.MyBeanUtil;
import lombok.Builder;

/**
 * @Author：胡侯东
 * @Date：2021/4/28 12:03 下午
 * @Desc: 获取access_token
 */
@Builder
public class AccessTokenProcessor {

    private AccessToken accessToken;

    public AccessTokenResult executor(AccessToken accessToken) {
        this.accessToken = accessToken;
        return this.executor();
    }

    protected AccessTokenResult executor() {
        String result = HttpUtil.get(String.format(TokenConstants.GET_ACCESS_TOKEN, accessToken.getGrant_type(), accessToken.getAppid(), accessToken.getSecret()));
        return MyBeanUtil.str2Bean(result, AccessTokenResult.class);
    }
}