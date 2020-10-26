package com.mz.common.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * excel导出工具类
 */
public class ExcelUtils {

    /**
     *功能描述 excel工具类，可导出带图片或不带图片的数据
     */
    public static java.lang.String excelOut(String sheetName, String[] titles, int rows, String fileName,
                                            List<Map<String,Object>> maps, HttpServletResponse response, String[] keys){

        HSSFWorkbook wb = null;
        try{
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            //创建工作sheet
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(sheetName);
            //设置单元格内容水平垂直居中
            HSSFCellStyle style = wb.createCellStyle();
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            HSSFRow row0 = sheet.createRow(0);
            row0.setHeightInPoints(30);
            if (titles.length == 0){
                return "error";
            }
            HSSFCell cell = null;
            //第一行、标题行列
            for (int i=0;i<titles.length;i++){
                cell = row0.createCell(i);     //第一个单元格
                cell.setCellValue(titles[i]);         //设定值
                cell.setCellStyle(getTitleStyle(wb));
                sheet.setColumnWidth(i,4000);
            }
            sheet.setColumnWidth(keys.length, 252*29+323);
            HSSFRow row = null;
            HSSFCell cellRow = null;
            HSSFClientAnchor anchor = null;
            HSSFCellStyle style1 = getStyle(wb);
            for (int i=1;i<=rows;i++){
                int cellColumn = 1;
                //创建行
                row = sheet.createRow(i);
                //设置默认行高
                row.setHeightInPoints(143);
                cellRow = row.createCell(0);
                cellRow.setCellStyle(style1);
                cellRow.setCellValue(i);
                //行数据处理
                Map<java.lang.String, Object> stringObjectMap = maps.get(i - 1);
                for(String value : keys){
                    //行单元格
                    cellRow = row.createCell(cellColumn);
                    cellRow.setCellStyle(style1);
                    //如果行数据中有图片时候的处理
                    if (value.equals("file")){
                        byte[] val = (byte[]) stringObjectMap.get(value);
                        if(val!=null){
                            anchor = new HSSFClientAnchor(10, 2, 1023, 255,(short) cellColumn, i, (short) cellColumn, i);
                            anchor.setAnchorType(3);
                            patriarch.createPicture(anchor, wb.addPicture(val, HSSFWorkbook.PICTURE_TYPE_JPEG));
                        }else{
                            cellRow.setCellValue("未绑定二维码");
                        }
                        cellColumn ++;
                        continue;
                    }
                    Object val = stringObjectMap.get(value);
                    if(val==null){
                        val = "";
                    }
                    cellRow.setCellValue(val.toString());
                    cellColumn ++;
                }

            }
            //输出文件
            /*response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" +
                    (URLEncoder.encode(fileName, "UTF-8")) + ";filename*=UTF-8''" +
                    (URLEncoder.encode(fileName, "UTF-8")));
            wb.write(response.getOutputStream());
            response.setContentType("application/ms-excel;charset=utf-8");*/
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            wb.write(bos);
            bos.flush();
            response.getOutputStream().write(bos.toByteArray());
            bos.close();
//            BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(bos.toByteArray()));
//            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//            File file = new File("D:/" + fileName);
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            byte[] bytes = new byte[1024];
//            int len = 0;
//            while ((len = in.read(bytes)) != -1) {
//                out.write(bytes, 0, len);
//                fileOutputStream.write(bytes, 0, len);
//            }
//            fileOutputStream.flush();
//            fileOutputStream.close();
//            out.flush();
//            bos.close();
//            out.close();
//            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

    private static HSSFCellStyle getTitleStyle(HSSFWorkbook wb){
        HSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font ztFont = wb.createFont();
        ztFont.setItalic(false);
        ztFont.setColor(Font.COLOR_NORMAL);
        ztFont.setFontHeightInPoints((short)14);
        ztFont.setFontName("宋体");
        ztFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        titleStyle.setFont(ztFont);
        return titleStyle;
    }

    private static HSSFCellStyle getStyle(HSSFWorkbook wb){
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font ztFont = wb.createFont();
        ztFont.setItalic(false);
        ztFont.setColor(Font.COLOR_NORMAL);
        ztFont.setFontHeightInPoints((short)12);
        ztFont.setFontName("宋体");
        style.setFont(ztFont);
        return style;
    }

}