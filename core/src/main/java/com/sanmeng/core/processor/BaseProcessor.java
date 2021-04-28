package com.sanmeng.core.processor;

import com.sanmeng.core.domain.BaseVo;
import com.sanmeng.core.domain.ResultVo;

/**
 * @Author：胡侯东
 * @Date：2021/4/27 4:16 下午
 * @Desc:
 */
public abstract class BaseProcessor {

    protected BaseVo baseVo;

    /**
     * 执行方法
     * @return
     */
    protected abstract ResultVo executor();

    public BaseProcessor build(String accessToken) {
        return this;
    }

    public static void main(String[] args) {
        BaseProcessor processor = null;
        processor.build("").executor();

    }

}