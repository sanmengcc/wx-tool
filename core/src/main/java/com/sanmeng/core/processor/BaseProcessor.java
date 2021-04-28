package com.sanmeng.core.processor;

import com.sanmeng.core.domain.BaseVo;
import com.sanmeng.core.domain.ResultVo;
import lombok.Builder;

/**
 * @Author：胡侯东
 * @Date：2021/4/27 4:16 下午
 * @Desc:
 */
public abstract class BaseProcessor {

    protected BaseVo baseVo;

    /**
     * 执行7⃣器
     * @return
     */
    protected abstract ResultVo executor();

    /**
     * 执行之前
     * @param baseVo
     */
    protected void before(BaseVo baseVo) {
        this.baseVo = baseVo;
    }

    /**
     * 执行以后
     * @param resultVo
     */
    protected void after(ResultVo resultVo){

    }

    /**
     * 获取请求的接口
     * @param url
     * @return
     */
    protected String getRequest(String url) {
        return String.format(url, this.baseVo.getAccess_token());
    }
}