package com.mz.common.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class WordUtils {
    /**
     *   将文本信息转为word输出
     *
     * @throws Exception
     */
    public String createDoc(Map<String,Object> dataMap,String type, HttpServletResponse response) {
        Configuration configuration = new Configuration(Configuration.getVersion());

        Template t = null;

        String fileName ="";
        try {
            configuration.setClassForTemplateLoading(WordUtils.class,"/template");
            configuration.setDefaultEncoding("UTF-8");
            t = configuration.getTemplate(type, "UTF-8"); //获取模板文件
            t.setOutputEncoding("UTF-8");

            Writer out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(),"UTF-8"));
            t.process(dataMap, out);  //将填充数据填入模板文件并输出到目标文件
           // out.close();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    /**
     * 导出word 并提供下载
     * @param response
     * @param dataMap 数据
     * @param fileName 导出文件名
     * @param type 模板
     */
    public void download(HttpServletResponse response,Map<String,Object> dataMap,String fileName,String type) {
        ServletOutputStream out = null;
        try {
            //输出文件
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8")+ "")));

            createDoc(dataMap,type, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }






    //获得图片的base64编码
    private String getBase64(String imgUrl) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        URL url = null;
        InputStream in = null;
        HttpURLConnection httpUrl = null;
        byte[] by = new byte[1024];

        try {
            url = new URL(imgUrl);
            httpUrl = (HttpURLConnection)url.openConnection();
            httpUrl.connect();
            in = httpUrl.getInputStream();
            int len = -1;
            while((len = in.read(by)) != -1) {
                data.write(by, 0, len);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }


}
