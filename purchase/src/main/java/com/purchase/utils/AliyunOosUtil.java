package com.purchase.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.purchase.common.log.MyLogger;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/12/13 0013.
 */
public class AliyunOosUtil {



    private static final String accessKeyId="LTAI4GF56crA1CePbdShZR6F";
    private static final String accessKeySecret="9WzRurFsv5LhGL6IgicbNsqKiHltf8";
    private static final String bucketName="purchase-image";
    private static final String endpoint = "http://oss-cn-hongkong.aliyuncs.com";

    public static void uploadFile(File file){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, file.getName(), file);
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        System.out.println(file.getName()+"上传成功");
    }
    public static void uploadFile(InputStream inputStream,String fileName){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream);
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        System.out.println(fileName+"上传成功");
    }


    public static void uploadFile(File file,String fileName){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file);
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        System.out.println(file.getName()+"上传成功");
    }

    public static void dowloadFile(String fileName,HttpServletResponse response){
        MyLogger myLogger=new MyLogger(AliyunOosUtil.class);
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        OSSObject ossObject = ossClient.getObject(bucketName, fileName);
        InputStream is=ossObject.getObjectContent();
        downloadFileByOutputStream(response,fileName,is);
        try{
            ossClient.shutdown();
            ossObject.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteFile(String fileName){
        if(StringUilts.isEmptyOrNull(fileName)) {
            return;
        }
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, fileName);
        ossClient.shutdown();
        System.out.println(fileName+"删除成功");
    }

    /**
     * 删除所有文件
     */
    private static void deleteAllFile(){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 列举所有包含指定前缀的文件并删除。
        String nextMarker = null;
        ObjectListing objectListing = null;
        do {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName).withMarker(nextMarker);

            objectListing = ossClient.listObjects(listObjectsRequest);
            if (objectListing.getObjectSummaries().size() > 0) {
                List<String> keys = new ArrayList<String>();
                for (OSSObjectSummary s : objectListing.getObjectSummaries()) {
                    System.out.println("准备删除文件: " + s.getKey());
                    keys.add(s.getKey());
                }
                DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName).withKeys(keys);
                System.out.println("删除文件成功："+keys.size());
                ossClient.deleteObjects(deleteObjectsRequest);
            }
            nextMarker = objectListing.getNextMarker();
        } while (objectListing.isTruncated());

        // 关闭OSSClient。
        ossClient.shutdown();
    }



    public static  void downloadFileByOutputStream(HttpServletResponse response,String outFileName,InputStream is){
        OutputStream os=null;
        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(outFileName, "UTF-8"));
            int len = 0;
            byte[] buffer = new byte[1024];
            os = response.getOutputStream();
            while ((len = is.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(is!=null){
                    is.close();
                }
                if(os!=null){
                    os.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
