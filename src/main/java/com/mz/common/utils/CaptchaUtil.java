package com.mz.common.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


/**
 * 关于验证码的工具类
 */
public final class CaptchaUtil
{
    private CaptchaUtil(){}
    /*
     * 随机字符字典
     */
    private static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    /*
     * 随机数
     */
    private static Random random = new Random();
    /*
     * 获取随机数
     */
    private static char[] getRandomString(int num)
    {
        char [] temps=new char[num];
        for(int i = 0; i < num; i++)
        {
            temps[i]=CHARS[random.nextInt(CHARS.length)];
        }
        return temps;
    }

    /*
     * 获取随机数颜色
     */
    private static Color getRandomColor()
    {
        return new Color(random.nextInt(230),random.nextInt(230),
                random.nextInt(230));
    }

    /*
     * 返回某颜色的反色
     */
    private static Color getReverseColor(Color c)
    {
        return new Color(255 - c.getRed(), 255 - c.getGreen(),
                255- c.getBlue());
    }

    public static String outputCaptcha(HttpServletResponse response, int num) throws IOException
    {
        response.setContentType("image/jpeg");
        int fontSize = new Long(Math.round(4.0 / num * 23.0)).intValue();
        char[] temps = getRandomString(num);
        int width = 90;
        int height = 38;

        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
        g.setColor(new Color(255, 255,255));
        g.fillRect(0, 0, width, height);
        for (int c =0, len = temps.length; c < len;c++){
            g.setColor(getRandomColor());
            g.drawString(temps[c]+"", 3 + c * fontSize, random.nextInt(20)+15);
        }
        for (int i = 0; i < 25 ; i++) {
            g.setColor(getRandomColor());
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        /*for (int i = 0, n = random.nextInt(60); i < n; i++)
        {
            g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
        }*/
        // 转成JPEG格式
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpeg", out);
        out.flush();
        return new String(temps);
    }
}