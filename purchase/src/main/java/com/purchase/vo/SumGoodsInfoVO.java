package com.purchase.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SumGoodsInfoVO {

    private String name;

    private double totalNumber;

    private String realUnit;

    private double totalPrice;

    private String ymDate;
}
