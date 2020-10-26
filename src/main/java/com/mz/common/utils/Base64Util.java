package com.mz.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

public class Base64Util {

    /**
     * 将base64字符串转换成图片
     * @param imgStr base64字符串
     * @param imgFilePath 图片存储路径
     * @param imgFileName 图片名
     * @return
     */
    public static boolean base64ToImage(String imgStr, String imgFilePath, String imgFileName){
        //图像数据为空
        if(StringUtils.isEmpty(imgStr)){
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for(int i = 0; i < bytes.length; ++i){
                //调整异常数据
                if(bytes[i] < 0){
                    bytes[i] += 256;
                }
            }
            File file = new File(imgFilePath);
            //如果文件夹不存在，则创建
            if(!file.exists()){
                file.mkdirs();
            }
            OutputStream outputStream = new FileOutputStream(imgFilePath + "/" + imgFileName);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将base64数据转换成图片流输出
     * @param imgStr
     * @param response
     */
    public static void base64ToImageStream(String imgStr, HttpServletResponse response){
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            byte[] bytes = decoder.decodeBuffer(imgStr.replaceAll("data:image/png;base64,", ""));
            for(int i = 0; i < bytes.length; ++i){
                //调整异常数据
                if(bytes[i] < 0){
                    bytes[i] += 256;
                }
            }
            InputStream inputStream = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(inputStream);
            //转换成png格式的IO流
            response.setContentType("image/png;charset=utf-8");
            ImageIO.write(image, "png", response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 将图片转换成base64字符串
     * @param imgFilePath
     * @return
     */
    public static String imageToBase64(String imgFilePath){
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try{
            in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        //返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    /**
     * 将流对象转换成base64字符串
     * @param in
     * @return
     */
    public static String getBase64(InputStream in){
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        //读取图片字节数组
        try{
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        //返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    public static boolean isImage(byte[] byteArray) throws Exception {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(byteArray);
            BufferedImage bufImg = ImageIO.read(byteArrayInputStream);
            if (bufImg == null) {
                return false;
            }
            bufImg = null;
        }  finally {
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
        }
        return true;
    }

}
