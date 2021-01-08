package com.purchase.enums;

/**
 * Created by Miracle on 2020/8/12.
 */
public enum SystemInfoEnum {

    FUND_RAKE_BACK_1("FUND_RAKE_BACK_1"),//基金一级返佣比例
    FUND_RAKE_BACK_2("FUND_RAKE_BACK_2"),//基金二级返佣比例
    FUND_RAKE_BACK_3("FUND_RAKE_BACK_3"),//基金三级返佣比例
    FUND_RAKE_BACK_4("FUND_RAKE_BACK_4"),//基金四级返佣比例
    TRANSACTION_FEE("TRANSACTION_FEE"),//交易手续费（1-1000）
    SYSTEM_TIPS_MUSIC("SYSTEM_TIPS_MUSIC"),//系统提示音（1或者2）
    INVITATION_USER_1("INVITATION_USER_1"),//邀请用第一个户注册送多少UT
    INVITATION_USER_2("INVITATION_USER_2"),//邀请用第二个户注册送多少UT
    INVITATION_USER_3("INVITATION_USER_3"),//邀请用第三个级以上户注册送多少UT
    IS_REGISTRATION_OPEN("IS_REGISTRATION_OPEN"),//是否开放注册（1=开启 2=关闭）
    IS_SELL_OPEN("IS_SELL_OPEN"),//是否开启出售（1=开启 2=关闭）
    IS_DRAWING_OPEN("IS_DRAWING_OPEN"),//是否开启提款（1=开启 2=关闭）
    DO_YOU_WANT_TO_TURN_ON_RECHARGE("DO_YOU_WANT_TO_TURN_ON_RECHARGE"),//是否开启充值（1=开启 2=关闭）
    IS_ROBOT_OPEN("IS_ROBOT_OPEN"),//是否开启自动发布订单(1=开启 2=关闭)
    OPEN_TIME("OPEN_TIME"),//运营时间
    PEOPLE_NUMBER("PEOPLE_NUMBER"),//注册人数
    K_LINE_INFO("K_LINE_INFO"),//k线图后台配置
    ;

    private String value;

    private SystemInfoEnum(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
