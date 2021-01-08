package com.purchase.utils;

import com.purchase.common.log.MyLogger;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by South on 2020/9/11.
 * 文件处理工具
 */
public class FileUtil {

    static MyLogger myLogger = new MyLogger(FileUtil.class);

    /**
     * 校验上传文件且返回文件名
     * @param file
     * @param type 允许上传类型，用空格隔开 .jpg .png .jpeg
     * @return
     */
    public static String fileVerify(MultipartFile file, String type) {
        InputStream inputStream = null;
        try{
            if ((file == null) || (file.getSize() <= 0L)) {
                throw new RuntimeException("请选择上传的文件");
            }
            String originalFilename = file.getOriginalFilename();
            String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            if (!type.contains(substring)) {
                throw new RuntimeException("长传文件格式只支持：" + type.replace(".", " "));
            }
            originalFilename = UUID.randomUUID().toString().replace("-", "") + substring;
            inputStream = file.getInputStream();
            AliyunOosUtil.uploadFile(inputStream,originalFilename);
            return originalFilename;
        }catch (Exception e) {
            throw new RuntimeException("上传未知错误");
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (IOException e) {
                myLogger.error(e.toString());
            }
        }
    }
}
