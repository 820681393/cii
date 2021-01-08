package com.purchase.enums;

/**
 * Created by Miracle on 2020/8/12.
 */
public enum RedisKeyEnum {

    K_LIEN("K_LIEN"),
    EXCHANGE_RATE("EXCHANGE_RATE"),
    TEST("test");

    private String value;

    private RedisKeyEnum(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
