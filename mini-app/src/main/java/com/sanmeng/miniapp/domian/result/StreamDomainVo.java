package com.sanmeng.miniapp.domian.result;

import com.sanmeng.core.domain.ResultVo;
import lombok.Builder;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/4/27 5:22 下午
 * @Desc:
 */
@Data
@Builder
public class StreamDomainVo extends ResultVo {

    private byte[] data;

}