package com.sanmeng.app.domian.link;

import com.sanmeng.core.domain.BaseValue;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/5/21 3:13 下午
 * @Desc:
 */
@Data
public class LinkCloudBaseVo extends BaseValue {

    /**
     * 是	云开发环境
     */
    private String env;

    /**
     * 静态网站自定义域名，不填则使用默认域名
     */
    private String domain;

    /**
     * 云开发静态网站 H5 页面路径，不可携带 query
     */
    private String path;

    /**
     * 云开发静态网站 H5 页面 query 参数，最大 1024 个字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~
     */
    private String query;
}