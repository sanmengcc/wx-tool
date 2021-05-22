package com.sanmeng.miniapp.processor.qr;

import com.alibaba.fastjson.JSONObject;
import com.sanmeng.core.domain.font.QrFontVo;
import com.sanmeng.core.processor.BaseProcessor;
import com.sanmeng.core.util.HttpUtil;
import com.sanmeng.core.util.MyBeanUtil;
import com.sanmeng.core.util.ValidateHelper;
import com.sanmeng.core.util.file.FileUtil;
import com.sanmeng.core.util.image.ImageUtil;
import com.sanmeng.miniapp.constants.QrConstants;
import com.sanmeng.miniapp.domian.qr.UnLimitQrMergeVo;
import com.sanmeng.miniapp.domian.qr.req.UnLimitQrReqVo;
import com.sanmeng.miniapp.domian.result.StreamDomainVo;
import lombok.Builder;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 生成无限的小程序二维码、并且更换logo
 * @Author：胡侯东
 * @Date：2021/4/27 4:15 下午
 * @Desc: https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.getUnlimited.html
 */
@Builder
public class UnLimitQrMergeProcessor extends BaseProcessor {

    public static void main(String[] args) {
        List<QrFontVo> qrFontVoList = new ArrayList<>();
        QrFontVo qrFontVo = new QrFontVo();
        qrFontVo.setTitle("111");
        qrFontVo.setX(40);
        qrFontVo.setY(40);
        qrFontVoList.add(qrFontVo);
        UnLimitQrMergeVo build = UnLimitQrMergeVo
                .builder()
                .accessToken("45_bX_JjFb9-O2SJ_IAXDKPli6gBG4t1mEB1Mip6E95RLYLz1htio3zf594WrJQmrzH-lyRxWaiu9pjt3xcMuzMQNxls8-8v3htJwJkWzTCjCjeGxY85HyqiLYgkZeWS3BC_ptv4G-X71iQ-Oz0UKJaAGAZPO")
                .page("pages/welcome/welcome")
                .scene("4401")
                .width("420")
                .fonts(qrFontVoList)
                .build();
        StreamDomainVo domainVo = UnLimitQrMergeProcessor.builder().build().executor(build);
        byte[] bytes = domainVo.getData();
        FileUtil.byte2File(bytes, "/users/huhd/work/git/wx-tool", "1.png");
    }

    public StreamDomainVo executor(UnLimitQrMergeVo baseVo) {
        super.before(baseVo);
        StreamDomainVo result = this.executor();
        super.after(result);
        return result;
    }

    @Override
    protected StreamDomainVo executor() {
        UnLimitQrMergeVo unLimitQrVo = (UnLimitQrMergeVo) baseVo;
        byte[] bytes = HttpUtil.payload(super.getRequest(QrConstants.UN_LIMIT), JSONObject.toJSONString(MyBeanUtil.createBean(unLimitQrVo, UnLimitQrReqVo.class)));
        //处理文字
        if (ValidateHelper.isNotEmptyCollection(unLimitQrVo.getFonts())) {
            bytes = ImageUtil.bufferedImage2Byte(ImageUtil.mergeImageFont(bytes, unLimitQrVo.getFonts()));
        }
        //处理logo
        if ((Objects.nonNull(unLimitQrVo.getMergeImage()) && unLimitQrVo.getMergeImage().length > 0) || ValidateHelper.isNotEmptyString(unLimitQrVo.getMergeImageUrl())) {
            BufferedImage qrCode = ImageUtil.byte2BufferedImage(bytes);
            if (ValidateHelper.isNotEmptyString(unLimitQrVo.getMergeImageUrl())) {
                byte[] mergeImage = ImageUtil.mergeImage(qrCode, unLimitQrVo.getMergeImageUrl());
                return StreamDomainVo.builder().data(mergeImage).build();
            }
            byte[] mergeImage = ImageUtil.mergeImage(qrCode, unLimitQrVo.getMergeImage());
            return StreamDomainVo.builder().data(mergeImage).build();
        }
        return StreamDomainVo.builder().data(bytes).build();
    }
}