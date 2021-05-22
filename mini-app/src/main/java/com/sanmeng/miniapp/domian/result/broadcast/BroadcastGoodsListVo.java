package com.sanmeng.miniapp.domian.result.broadcast;

import com.sanmeng.core.domain.BaseValue;
import lombok.Data;

/**
 * @Author：胡侯东
 * @Date：2021/5/22 1:25 下午
 * @Desc:
 */
@Data
public class BroadcastGoodsListVo extends BaseValue {

    /**
     * 商品封面图链接
     */
    private String cover_img;

    /**
     * 商品小程序路径
     */
    private String url;

    /**
     * 商品价格（分）
     */
    private Integer price;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格，使用方式看price_type
     */
    private String price2;

    /**
     * 价格类型，1：一口价（只需要传入price，price2不传） 2：价格区间（price字段为左边界，price2字段为右边界，price和price2必传） 3：显示折扣价（price字段为原价，price2字段为现价， price和price2必传）
     */
    private Integer price_type;

    /**
     * 商品id
     */
    private Integer goods_id;

    /**
     * 第三方商品appid ,当前小程序商品则为空
     */
    private String third_party_appid;
    
}