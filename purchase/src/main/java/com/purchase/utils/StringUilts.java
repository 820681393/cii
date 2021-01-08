package com.purchase.utils;

import net.sf.json.JSONObject;

import java.net.URLDecoder;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUilts {

    /**
     * 判断字符串是否为null或者""
     */
    public static boolean isEmptyOrNull(String content) {
        return content == null || content.equals("") || content.trim().equals("null");
    }

    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString();    //获取UUID并转化为String对象
        uuid = uuid.replace("-", "");
        return uuid;
    }

    /**
     * 解决编码问题，去掉换行
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 目前用于
     * 处理请求报文，取数据base64编码字符串
     * 随机字符（数字+字母）* 位 + 数据base64编码字符串 + 随机字符（数字+字母）* 位
     */
    public static String getStrData(String str, int start, int end) {
        String strData = "";
        try {
            String strs = URLDecoder.decode(str, "utf-8");
            JSONObject jsonData = JSONObject.fromObject(strs);
            String objData = jsonData.get("obj").toString();
            System.out.println("objData=" + objData);
            String newStr = objData.substring(start, objData.length());
            newStr = newStr.substring(0, newStr.length() - end);
            Base64.Decoder decoder = Base64.getDecoder();
            strData = new String(decoder.decode(newStr), "utf-8");
//            BASE64Decoder decoder = new BASE64Decoder();
//            strData=new String(decoder.decodeBuffer(newStr),"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strData;
    }


    public static String getRandomNubmer(int num) {
        char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9'};
        Random random = new Random();
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < num; i++) {
            String strRand = String.valueOf(codeSequence[random
                    .nextInt(codeSequence.length)]);
            str.append(strRand);
        }
        return str.toString();
    }


    /**
     * TokenHeader
     * Token随机数 数字加字母
     */
    public static String getHeaderNumber(int n) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) { // 产生字母
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) (nextInt + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(str)) { // 产生数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
