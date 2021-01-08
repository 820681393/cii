package com.purchase.utils;

import java.math.BigDecimal;

/**
 * Created by Miracle on 2020/8/17.
 */
public class BigDecimalUtil {

    /**
     *BigDecimal 大于比较
     * @param a
     * @param b
     * @return
     */
    public static boolean dy(BigDecimal a,BigDecimal b){
        if(a.compareTo(b) == 1){
            return true;
        }
        return false;
    }

    /**
     *BigDecimal 大于等于比较
     * @param a
     * @param b
     * @return
     */
    public static boolean dydy(BigDecimal a,BigDecimal b){
        if(a.compareTo(b) >= -1){
            return true;
        }
        return false;
    }


    /**
     *BigDecimal 小于比较
     * @param a
     * @param b
     * @return
     */
    public static boolean xy(BigDecimal a,BigDecimal b){
        if(a.compareTo(b) == -1){
            return true;
        }
        return false;
    }

    /**
     *BigDecimal 小于等于比较
     * @param a
     * @param b
     * @return
     */
    public static boolean xydy(BigDecimal a,BigDecimal b){
        if(a.compareTo(b) < 1){
            return true;
        }
        return false;
    }


    /**
     *BigDecimal 加法
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal add(BigDecimal a,BigDecimal b){
        return a.add(b);
    }


    /**
     *BigDecimal 减法
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal down(BigDecimal a,BigDecimal b){
        return a.subtract(b);
    }

    /**
     *BigDecimal 乘法
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal multiply(BigDecimal a,BigDecimal b){
        return a.multiply(b);
    }



    /**
     *BigDecimal 除法
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal divide(BigDecimal a,BigDecimal b){
        return a.divide(b,6,BigDecimal.ROUND_HALF_UP);
    }
}
