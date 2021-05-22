package com.sanmeng.core.util.image;

import com.sanmeng.core.domain.font.QrFontVo;
import com.sanmeng.core.exception.WxException;
import com.sanmeng.core.util.font.FontUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @Author：胡侯东
 * @Date：2021/4/27 5:45 下午
 * @Desc:
 */
public class ImageUtil {

    private static String fontUrl = "https://liefeng.oss-cn-shenzhen.aliyuncs.com/zjsm/PROD/zjsm/20200226/MrhpCF8ctY.ttf";

    public static void main(String[] args) throws IOException {
        URL url= new URL("https://property-liefeng.oss-cn-shenzhen.aliyuncs.com/PROD/zjsm_oldpeople/20210518/20210518154549q34X2fsk.png");//url 为图片的URL 地址

        BufferedImage image=(BufferedImage)ImageIO.read(url);
        System.out.println(image.getWidth());
    }

    /**
     * 字节数组转BufferedImage
     * @param image
     * @return
     */
    public static BufferedImage byte2BufferedImage(byte[] image) {
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(image);
            BufferedImage bufferedImage = ImageIO.read(stream);
            return bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WxException("图片资源转换失败！请检查您的数据资源是否正确。");
        }
    }

    /**
     * BufferedImage转字节数组
     * @param image
     * @return
     */
    public static byte[] bufferedImage2Byte(BufferedImage image) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image,"png",outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new WxException("图片资源转换失败！请检查您的数据资源是否正确。");
        }
    }

    /**
     * 对图片进行文字的合并
     * @param pic
     * @param fontVos
     * @return
     */
    public static BufferedImage mergeImageFont(byte [] pic, List<QrFontVo> fontVos){
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new ByteArrayInputStream(pic));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage image = new BufferedImage(500, 550, BufferedImage.TYPE_INT_RGB);
        //设置图片的背景色
        Graphics2D main = image.createGraphics();
        main.setColor(Color.white);
        main.fillRect(0, 0, 500, 550);
        Graphics mainPic = image.getGraphics();
        if(bi != null){
            mainPic.drawImage(bi, 40, 40, 400, 400, null);
            mainPic.dispose();
        }
        Graphics titleG = image.createGraphics();
        //设置区域颜色
        //titleG.setColor(Color.white);
        //填充区域并确定区域大小位置
        //titleG.fillRect(450, 50, 450, 50);
        //设置字体颜色，先设置颜色，再填充内容
        titleG.setColor(Color.BLACK);
        //设置字体  LINUX系统无字体，有的需要安装还需要权限
        Font titleFont = FontUtil.getFont(fontUrl);
        titleG.setFont(titleFont);
        for (QrFontVo fontVo : fontVos) {
            titleG.drawString(fontVo.getTitle(), fontVo.getX(), fontVo.getY());
        }
        return image;
    }

    /**
     * 合并图片
     * @param qrCode
     * @param image
     * @return
     */
    public static byte[] mergeImage(BufferedImage qrCode, byte[] image) {
        // 将头像转为圆角
        BufferedImage avatarImage = copeImage(image);
        //创建Graphics2D绘图
        Graphics2D graphics = qrCode.createGraphics();
        // 设置抗锯齿的属性
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // 二维码宽度
        int qrWidth = qrCode.getWidth();
        // 二维码高度
        int qrHeight = qrCode.getHeight();
        // 组合之后logo的宽度(占二维码的1/2.2)
        int logoQrWidth = (int) (qrWidth / 2.2);
        // 组合之后logo的高度
        int logoQrHeight = (int) (qrHeight / 2.2);
        // 左边距、上边距、宽度、高度。覆盖原始图片中间的logo
        graphics.drawImage(
                avatarImage,
                (qrWidth - logoQrWidth) / 2,
                (qrHeight - logoQrHeight) / 2,
                logoQrWidth,
                logoQrHeight,
                null);
        //关闭
        graphics.dispose();
        return ImageUtil.bufferedImage2Byte(qrCode);
    }

    /**
     * 合并图片
     * @param qrCode
     * @param image
     * @return
     */
    public static byte[] mergeImage(BufferedImage qrCode, String image) {
        // 将头像转为圆角
        BufferedImage avatarImage = copeImage(image);
        //创建Graphics2D绘图
        Graphics2D graphics = qrCode.createGraphics();
        // 设置抗锯齿的属性
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // 二维码宽度
        int qrWidth = qrCode.getWidth();
        // 二维码高度
        int qrHeight = qrCode.getHeight();
        // 组合之后logo的宽度(占二维码的1/2.2)
        int logoQrWidth = (int) (qrWidth / 2.2);
        // 组合之后logo的高度
        int logoQrHeight = (int) (qrHeight / 2.2);
        // 左边距、上边距、宽度、高度。覆盖原始图片中间的logo
        graphics.drawImage(
                avatarImage,
                (qrWidth - logoQrWidth) / 2,
                (qrHeight - logoQrHeight) / 2,
                logoQrWidth,
                logoQrHeight,
                null);
        //关闭
        graphics.dispose();
        return ImageUtil.bufferedImage2Byte(qrCode);
    }

    /**
     * 图片处理为圆角
     * @param image
     * @return
     */
    public static BufferedImage copeImage(String image) {
        // 从链接中获取图片
        BufferedImage avatarImage;
        try {
            avatarImage = ImageIO.read(new URL(image));
            int width = avatarImage.getWidth();
            int height = avatarImage.getHeight();
            // 透明底的图片
            BufferedImage formatAvatarImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics = formatAvatarImage.createGraphics();
            // 把图片切成一个圆
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 留一个像素的空白区域，这个很重要，画圆的时候把这个覆盖
            int border = 1;
            // 图片是一个圆型
            Ellipse2D.Double shape = new Ellipse2D.Double(border, border, width - border * 2, width - border * 2);
            // 需要保留的区域
            graphics.setClip(shape);
            graphics.drawImage(avatarImage, border, border, width - border * 2, width - border * 2, null);
            graphics.dispose();

            // 在圆图外面再画一个圆
            // 新创建一个graphics，这样画的圆不会有锯齿
            graphics = formatAvatarImage.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int border1 = 3;
            //画笔是4.5个像素，BasicStroke的使用可以查看下面的参考文档
            //使画笔时基本会像外延伸一定像素，具体可以自己使用的时候测试
            Stroke s = new BasicStroke(4.5F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            graphics.setStroke(s);
            graphics.setColor(Color.WHITE);
            graphics.drawOval(border1, border1, width - border1 * 2, width - border1 * 2);
            graphics.dispose();
            return formatAvatarImage;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 图片处理为圆角
     * @param image
     * @return
     */
    public static BufferedImage copeImage(byte[] image) {
        // 从链接中获取图片
        BufferedImage avatarImage;
        try {
            avatarImage = ImageIO.read(new ByteArrayInputStream(image));
            int width = avatarImage.getWidth();
            int height = avatarImage.getHeight();
            // 透明底的图片
            BufferedImage formatAvatarImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics = formatAvatarImage.createGraphics();
            // 把图片切成一个圆
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 留一个像素的空白区域，这个很重要，画圆的时候把这个覆盖
            int border = 1;
            // 图片是一个圆型
            Ellipse2D.Double shape = new Ellipse2D.Double(border, border, width - border * 2, width - border * 2);
            // 需要保留的区域
            graphics.setClip(shape);
            graphics.drawImage(avatarImage, border, border, width - border * 2, width - border * 2, null);
            graphics.dispose();

            // 在圆图外面再画一个圆
            // 新创建一个graphics，这样画的圆不会有锯齿
            graphics = formatAvatarImage.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int border1 = 3;
            //画笔是4.5个像素，BasicStroke的使用可以查看下面的参考文档
            //使画笔时基本会像外延伸一定像素，具体可以自己使用的时候测试
            Stroke s = new BasicStroke(4.5F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            graphics.setStroke(s);
            graphics.setColor(Color.WHITE);
            graphics.drawOval(border1, border1, width - border1 * 2, width - border1 * 2);
            graphics.dispose();
            return formatAvatarImage;
        } catch (Exception e) {
        }
        return null;
    }
}