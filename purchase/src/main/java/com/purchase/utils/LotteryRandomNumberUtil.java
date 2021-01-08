package com.purchase.utils;

import java.util.Arrays;
import java.util.Random;

public class LotteryRandomNumberUtil {


    /**
     *
     * @param length 返回数组长度
     * @param numebrs 随机种子数组
     * @return 返回随机数组
     */
    public static String[] getRandomNumberNoRepeat(int length,String [] numebrs){
        Random random = new Random();
        for(int i = 0; i < numebrs.length; i++){
            int p = random.nextInt(length);
            String tmp = numebrs[i];
            numebrs[i] = numebrs[p];
            numebrs[p] = tmp;
        }
        String[] returnNumbers=new String[length];
        for (int i = 0; i < length; i++) {
            returnNumbers[i] = numebrs[i];
        }
        random=null;
        return returnNumbers;
    }
    /**
     *
     * @param length 返回数组长度
     * @param numebrs 随机种子数组
     * @return 返回随机数组
     */
    public static String[] getRandomNumberRepeat(int length,String [] numebrs){
        Random random = new Random();
        String[] returnNumbers=new String[length];
        for(int i = 0; i < length; i++){
            int p = random.nextInt(numebrs.length);
            returnNumbers[i]=numebrs[p];
        }
        random=null;
        return returnNumbers;
    }

    public static void main(String[] args) {
        String[] numbers={"1","2","3","4","5","6","7","8"};
        String[] numbers1=getRandomNumberRepeat(8,numbers);
        String[] numbers2=getRandomNumberNoRepeat(8,numbers);
        for (String s : numbers1) {
            System.out.println(s);
        }
        System.out.println("=============");
        for (String s : numbers2) {
            System.out.println(s);
        }
    }


}
