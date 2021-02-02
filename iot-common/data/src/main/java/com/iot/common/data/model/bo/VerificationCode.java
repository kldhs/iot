package com.iot.common.data.model.bo;

import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class VerificationCode {

    private static final int WEIGHT = 70;             //验证码图片的长和宽
    private static final int HEIGHT = 30;
    private String text;                //用来保存验证码的文本内容
    private final Random r = new Random();      //获取随机数对象
    private static final String CODES = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";    //验证码数组

    /**
     * 获取随机的颜色
     */
    private Color randomColor() {
        //这里为什么是150，因为当r，g，b都为255时，即为白色，为了好辨认，需要颜色深一点。
        int r = this.r.nextInt(150);
        int g = this.r.nextInt(150);
        int b = this.r.nextInt(150);
        //返回一个随机颜色
        return new Color(r, g, b);
    }

    /**
     * 获取随机字符
     */
    private char randomChar() {
        int index = r.nextInt(CODES.length());
        return CODES.charAt(index);
    }

    /**
     * 画干扰线，验证码干扰线用来防止计算机解析图片
     */
    private void drawLine(BufferedImage image) {
        int num = 155;
        //定义干扰线的数量
        Graphics2D g = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            int x = r.nextInt(WEIGHT);
            int y = r.nextInt(HEIGHT);
            int xl = r.nextInt(WEIGHT);
            int yl = r.nextInt(HEIGHT);
            g.setColor(getRandColor(160, 200));
            g.drawLine(x, y, x + xl, y + yl);
        }
    }

    /**
     * 创建图片的方法
     */
    private BufferedImage createImage() {
        //创建图片缓冲区
        BufferedImage image = new BufferedImage(WEIGHT, HEIGHT, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        // 设定图像背景色(因为是做背景，所以偏淡)
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, WEIGHT, HEIGHT);
        //返回一个图片
        return image;
    }

    /**
     * 获取验证码图片的方法
     */
    public BufferedImage getImage(String cacheCode) {
        BufferedImage image = createImage();
        //获取画笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();
        drawLine(image);
        //定义字符的x坐标起始位置偏移
        int x = 1;
        var flag = false;
        if (StringUtils.isNotBlank(cacheCode) && cacheCode.length() == 4) {
            flag = true;
        }
        //画四个字符即可
        for (int i = 0; i < 4; i++) {
            //随机生成字符，因为只有画字符串的方法，没有画字符的方法，所以需要将字符变成字符串再画
            String s = flag?String.valueOf(cacheCode.charAt(i)):randomChar() + "";
            //添加到StringBuilder里面
            sb.append(s);
            //设置字体
            int fontSize = 18;
            Font font = new Font(g.getFont().getName(), Font.BOLD, fontSize);
            g.setFont(font);
            //设置颜色，随机
            g.setColor(randomColor());
            // 从height-5处往上写
            g.drawString(s, x, HEIGHT - 5);
            x += fontSize;
        }
        this.text = sb.toString();
        return image;
    }

    /**
     * 给定范围获得随机颜色
     */
    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }

        if (bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 获取验证码文本的方法
     */
    public String getText() {
        return text;
    }
}
