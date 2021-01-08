package com.purchase.enums;

/**
 * Created by Miracle on 2020/8/12.
 */
public enum EnvironmentalEnum {

    DEV("dev"),
    PROD("prod"),
    TEST("test");

    private String value;

    private EnvironmentalEnum(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
