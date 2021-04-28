package com.sanmeng.core.util.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @Author：胡侯东
 * @Date：2021/4/27 5:45 下午
 * @Desc:
 */
public class ImageUtil {

//    public static void main(String[] args) throws IOException {
//        BufferedImage image = ImageIO.read(new FileInputStream("sss.png"));
//        BufferedImage bufferedImage = ImageUtil.mergeImage(image, "http://oss.sdw.srngem.com/kimroly/dev/WechatIMG172.jpeg");
//        File outputfile = new File("image.jpg");
//        ImageIO.write(bufferedImage, "jpg", outputfile);
//    }

    /**
     * 合并图片
     * @param qrCode
     * @param image
     * @return
     */
    public static BufferedImage mergeImage(BufferedImage qrCode, byte[] image) {
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
        return qrCode;
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

    /**
     * 将图片处理成圆角
     * @param avatarUrl
     * @return
     */
    public static BufferedImage copeImage(String avatarUrl) {
        // 从链接中获取图片
        BufferedImage avatarImage;
        try {
            avatarImage = ImageIO.read(new URL(avatarUrl));
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