package com.sanmeng.app.domian.result.broadcast;

import com.sanmeng.core.domain.ResultVo;
import lombok.Data;

import java.util.List;

/**
 * @Author：胡侯东
 * @Date：2021/5/22 1:25 下午
 * @Desc:
 */
@Data
public class BroadcastListOneResultVo extends ResultVo {

    /**
     * 直播间名称
     */
    private String name;

    /**
     * 直播间ID
     */
    private Integer roomid;

    /**
     * 直播间背景图链接
     */
    private String cover_img;

    /**
     * 直播间分享图链接
     */
    private String share_img;

    /**
     * 直播间状态。101：直播中，102：未开始，103已结束，104禁播，105：暂停，106：异常，107：已过期
     */
    private String live_status;

    /**
     * 直播间开始时间，列表按照start_time降序排列
     */
    private Integer start_time;

    /**
     * 直播计划结束时间
     */
    private Integer end_time;

    /**
     * 主播名
     */
    private String anchor_name;

    /**
     * 直播类型，1 推流 0 手机直播
     */
    private String live_type;

    /**
     * 是否关闭点赞 【0：开启，1：关闭】（若关闭，观众端将隐藏点赞按钮，直播开始后不允许开启）
     */
    private Integer close_like;

    /**
     * 是否关闭货架 【0：开启，1：关闭】（若关闭，观众端将隐藏商品货架，直播开始后不允许开启）
     */
    private Integer close_goods;

    /**
     * 是否关闭评论 【0：开启，1：关闭】（若关闭，观众端将隐藏评论入口，直播开始后不允许开启）
     */
    private Integer close_comment;

    /**
     * 是否关闭客服 【0：开启，1：关闭】 默认关闭客服（直播开始后允许开启）
     */
    private Integer close_kf;

    /**
     * 是否关闭回放 【0：开启，1：关闭】默认关闭回放（直播开始后允许开启）
     */
    private Integer close_replay;

    /**
     * 是否开启官方收录，1 开启，0 关闭
     */
    private Integer is_feeds_public;

    /**
     * 创建者openid
     */
    private String creater_openid;

    /**
     * 官方收录封面
     */
    private String feeds_img;

    /**
     * 拉取房间总数
     */
    private Integer total;

    /**
     * 商品信息
     */
    private List<BroadcastGoodsListVo> goods;

}