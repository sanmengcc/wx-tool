package com.sanmeng.app.domian.link;

import com.sanmeng.core.domain.BaseVo;
import lombok.Builder;

/**
 * @Author：胡侯东
 * @Date：2021/5/21 3:08 下午
 * @Desc:
 */
@Builder
public class ReqLinkVo extends BaseVo {

    /**
     * 通过 URL Link 进入的小程序页面路径，必须是已经发布的小程序存在的页面，不可携带 query 。path 为空时会跳转小程序主页
     */
    private String path;

    /**
     * 通过 URL Link 进入小程序时的query，最大1024个字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~
     */
    private String query;

    /**
     * 生成的 URL Link 类型，到期失效：true，永久有效：false
     */
    private boolean is_expire;

    /**
     * 失效类型，失效时间：0，失效间隔天数：1
     */
    private Integer expire_type;

    /**
     * 生成的到期失效 URL Link 在该时间前有效。最长有效期为1年。expire_type 为 0 必填
     */
    private String expire_time;

    /**
     * 到期失效的URL Link的失效间隔天数。生成的到期失效URL Link在该间隔时间到达前有效。最长间隔天数为365天。expire_type 为 1 必填
     */
    private Integer expire_interval;

    /**
     * 云开发静态网站自定义 H5 配置参数，可配置中转的云开发 H5 页面。不填默认用官方 H5 页面
     */
    private LinkCloudBaseVo cloud_base;

    /**
     * 构建accessToken
     * @param accessToken
     * @return
     */
    public ReqLinkVo accessToken(String accessToken) {
        super.setAccess_token(accessToken);
        return this;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isIs_expire() {
        return is_expire;
    }

    public void setIs_expire(boolean is_expire) {
        this.is_expire = is_expire;
    }

    public Integer getExpire_type() {
        return expire_type;
    }

    public void setExpire_type(Integer expire_type) {
        this.expire_type = expire_type;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public Integer getExpire_interval() {
        return expire_interval;
    }

    public void setExpire_interval(Integer expire_interval) {
        this.expire_interval = expire_interval;
    }

    public LinkCloudBaseVo getCloud_base() {
        return cloud_base;
    }

    public void setCloud_base(LinkCloudBaseVo cloud_base) {
        this.cloud_base = cloud_base;
    }
}