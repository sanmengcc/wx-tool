package com.sanmeng.miniapp.domian.result.link;

import com.sanmeng.core.domain.ResultVo;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/5/21 3:23 下午
 * @Desc:
 */
@Data
public class LinkDomainVo extends ResultVo {

    /**
     * 跳转link
     */
    private String url_link;
}