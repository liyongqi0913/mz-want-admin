package com.mz.common.utils;

import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.List;
import java.util.UUID;

public class FileUtil {

    /**
     * 保存文件
     * @param multipartFile 文件
     * @param path 文件路径
     * @return 最终文件路径
     */
    public static String save(MultipartFile multipartFile, String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = multipartFile.getOriginalFilename();
        if (fileName == null) throw new RuntimeException("文件名不能为空");
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        path += File.separator + fileName;
        outputFile(path, multipartFile);
        return path;
    }

    /**
     * 文件转base64编码
     * @param file 文件
     * @return base64编码
     */
    public static String toBase64(File file) {
        if (null == file)
            return null;
        try {
            return toBase64(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toBase64(InputStream inputStream) {
        byte[] bytes = new byte[1024];
        int len = 0;
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            while ((len = inputStream.read(bytes)) > -1) {
                outputStream.write(bytes, 0, len);
            }
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                closeInputStream(inputStream);
                closeOutputStream(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 关闭输入流
     * @param inputStream 输入流对象
     * @throws IOException IO异常
     */
    public static void closeInputStream(InputStream inputStream) throws IOException {
        if (null != inputStream) {
            inputStream.close();
        }
    }

    /**
     * 关闭输出流
     * @param outputStream 输出流对象
     * @throws IOException IO异常
     */
    public static void closeOutputStream(OutputStream outputStream) throws IOException {
        if (null != outputStream) {
            outputStream.close();
        }
    }

    /**
     * 保存文件
     * @param multipartFile
     * @param uuid
     * @param catalog
     * @return
     * @throws FileNotFoundException
     */
    public static String saveFile(MultipartFile multipartFile, String uuid, String catalog) throws FileNotFoundException {
        String path = checkAndMkdir(uuid, catalog);
        String fileName = multipartFile.getOriginalFilename();
        if (fileName == null) throw new RuntimeException("文件名不能为空");
        path += File.separator + fileName;
        outputFile(path, multipartFile);
        return catalog + File.separator + uuid;
    }

    /**
     * 批量保存文件
     * @param multipartFiles 文件集合
     * @param uuid
     * @param catalog
     * @throws IOException
     */
    public static void saveBatchFile(MultipartFile[] multipartFiles, String uuid, String catalog) throws IOException {
        String path = checkAndMkdir(uuid, catalog);
        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = multipartFile.getOriginalFilename();
            if (fileName == null) throw new RuntimeException("文件名不能为空");
            String filePath = path + File.separator + fileName;
            // 将上传文件保存到目标文件目录
            outputFile(filePath, multipartFile);
        }
    }

    /**
     * 检查并创建文件夹
     * @param uuid 文件夹唯一标识
     * @param catalog 目录
     * @return 文件路径
     * @throws FileNotFoundException
     */
    public static String checkAndMkdir(String uuid, String catalog) throws FileNotFoundException {
        if(StringUtils.isEmpty(uuid)){
            uuid = UUID.randomUUID().toString().replaceAll("-", "");
        }
        String path = getRootPath() + File.separator + "upload" + File.separator + catalog + File.separator + uuid;
        File file = new File(path);
        if (!file.exists()) {
            boolean res = file.mkdirs();
        }
        return path;
    }

    public static void visitAllDirsAndFiles(File dir, List<File> files) {
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            for (File child : children) {
                visitAllDirsAndFiles(child, files);
            }
        }else{
            files.add(dir);
        }
    }

    /**
     * 文件输出保存
     * @param path 文件保存路径
     * @param multipartFile 文件
     */
    private static void outputFile(String path, MultipartFile multipartFile) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            outputStream = new FileOutputStream(path);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1 ) {
                outputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                closeInputStream(inputStream);
                closeOutputStream(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getRootPath() throws FileNotFoundException {
        File rootPath = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!rootPath.exists()) rootPath = new File("");
        return rootPath.getAbsolutePath();
    }

    /**
     * 通过文件路径直接修改文件名
     * @param filePath    需要修改的文件的完整路径
     * @param newFileName 需要修改的文件的名称
     */
    public static String fixFileName(String filePath, String newFileName) {
        File f = new File(filePath);
        // 判断原文件是否存在
        if (!f.exists()) {
            return null;
        }
        newFileName = newFileName.trim();
        if (StringUtils.isEmpty(newFileName)) // 文件名不能为空
            return null;
        String newFilePath = null;
        if (f.isDirectory()) { // 判断是否为文件夹
            newFilePath = filePath.substring(0, filePath.lastIndexOf(File.separator)) + File.separator + newFileName;
        } else {
            newFilePath = filePath.substring(0, filePath.lastIndexOf(File.separator)) + File.separator + newFileName
                    + filePath.substring(filePath.lastIndexOf("."));
        }
        File nf = new File(newFilePath);
        try {
            boolean res = f.renameTo(nf); // 修改文件名
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
        return newFilePath;
    }

}
