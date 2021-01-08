package com.purchase.enums;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Miracle on 2020/9/7.
 */
public enum InternationalizationEnum {


    SUCCESS("请求成功","請求成功","success"),
    FAIL("请求失败","請求失败","fail"),
    IS_NOT_NULL("不能为空","不能為空","is not null"),
    LOGIN_INVALID("登录失效","登錄失效","login invalid"),
    PLEASE_WAIT_FOR_REVIEW("请等待审核","請等待審核","Please wait for review"),
    THERE_IS_NO_NEED_TO_OPERATE_AGAIN("无需再次操作","無需再次操作","There is no need to operate again"),
    PLEASE_SELECT_A_PICTURE("请选择图片","請選擇圖片","Please select a picture"),
    THE_UPLOAD_FILE_SHOULD_NOT_BE_LARGER_THAN_10MB("上传文件不得大于10MB","上傳文件不得大於10MB","The upload file should not be larger than 10MB"),
    UPLOAD_FILE_FORMAT_ONLY_SUPPORTS_JPG_PNG_JPEG("上传文件格式只支持 JPG PNG JPEG","上傳文件格式只支持 JPG PNG JPEG","Upload file format only supports JPG PNG JPEG"),
    UPLOAD_FAILED("上传失败","上傳失敗","Upload failed"),
    YOU_HAVE_A_WITHDRAWAL_IN_PROGRESS_YOU_CANNOT_CONTINUE_TO_WITHDRAW("您有提款正在进行中无法继续提款","您有提款正在進行中無法繼續提款","You have a withdrawal in progress. You cannot continue to withdraw"),
    NO_CORRESPONDING_WALLET_FOUND("未找到对应钱包","未找到對應錢包","No corresponding wallet found"),
    CORRESPONDING_ACCOUNT_NOT_FOUND("未找到对应账户","未找到對應賬戶","Corresponding account not found"),
    INSUFFICIENT_ACCOUNT_BALANCE("账户余额不足","賬戶餘額不足","Insufficient account balance"),
    ACCOUNT_ERROR("钱包错误","錢包錯誤","Account error"),
    CURRENCY_DIFFERENCE_BETWEEN_WALLET_AND_ACCOUNT("钱包与账户币种不一致","錢包與賬戶幣種不一致","Currency difference between wallet and account"),
    LESS_THAN_MINIMUM_AMOUNT("小于最小金额","小於最小金額","Less than minimum amount"),
    GREATER_THAN_THE_MAXIMUM_AMOUNT("大于最大金额","大於最大金額","Greater than the maximum amount"),
    UNABLE_TO_QUERY("无法查询","無法查詢","Unable to query"),
    CANNOT_CANCEL("无法取消","無法取消","Cannot cancel"),
    PASSWORD_ERROR("密码错误","密碼錯誤","Password error"),
    LOW_POWER("电量不足","電量不足","Low power"),
    REFRESH_GRAPHIC_CAPTCHA("刷新图形验证码","刷新圖形驗證碼","Refresh graphic captcha"),
    GRAPHIC_VERIFICATION_CODE_ERROR("图形验证码错误","圖形驗證碼錯誤","Graphic verification code error"),
    TEL_VERIFICATION_CODE_ERROR("手机验证码错误","手機驗證碼錯誤","tel verification code error"),
    UNABLE_TO_SUBMIT_REPEATEDLY("无法重复提交","無法重複提交","Unable to submit repeatedly"),
    PLEASE_SELECT_THE_TYPE_OF_USDT("请选择USDT类型","請選擇USDT類型","Please select the type of usdt"),
    NO_CORRESPONDING_ORDER_FOUND("未找到对应订单","未找到對應訂單","No corresponding order found"),
    UNABLE_TO_PAY("无法付款","無法付款","Unable to pay"),
    UNABLE_TO_CONFIRM("无法确认","無法確認","Unable to confirm"),
    SUPERIOR_USER_NOT_FOUND("未找到上级用户","未找到上級用戶","Superior user not found"),
    THE_USER_NAME_ALREADY_EXISTS("用户名已存在","用戶名已存在","The user name already exists"),
    MOBILE_NUMBER_ALREADY_EXISTS("手机号已存在","手機號已存在","Mobile number already exists"),
    IS_REGISTRATION_OPEN("系统关闭注册","系統關閉註冊","System shutdown registration"),
    IS_RECHARGE_OPEN("系统关闭充值","系統關閉充值","System shutdown recharge"),
    IS_SELL_OPEN("系统关闭出售","系統關閉出售","System shutdown sell"),
    IS_DRAWING_OPEN("系统关闭提款","系統關閉提款","System shutdown drawing"),
    RECEIVABLES_INFORMATION_NOT_FOUND("未找到对应收款信息","未找到對應收款信息","Receivables information not found"),
    COLLECTION_INFORMATION_TYPE_DOES_NOT_MATCH("收款信息不符","收款信息不符","Collection information type does not match"),
    BEYOND_THE_PURCHASE("超出限购","超出限購","Beyond the purchase"),
    NO_CORRESPONDING_CATTLE_WERE_FOUND("未找到对应牛只","為找到對應牛只","No corresponding cattle were found"),
    NUMBER_OF_BETS_CLOSED("投注期数封盘","投注期數封盤","Number of bets closed"),
    NUMBER_OF_CLOSED("期数封盘","期數封盤","Number of bets closed"),
    HAS_THE_LOTTERY("已开奖","已開獎","Has the lottery"),
    BET_FAILURE("投注失败","投注失敗","Bet failure"),
    BET_INFO_ERROR("投注信息错误","投注信息錯誤","Bet info error"),
    WRONG_WAY_TO_PLAY("玩法错误","玩法錯誤","Wrong way to play"),
    LOTTERY_TYPE_ERROR("彩种类型错误","彩種類型錯誤","Lottery type error"),
    WRONG_NUMBER_OF_PERIODS("期数错误","期數錯誤","Wrong number of periods"),
    PROGRAM_EXCEPTION("程序异常","程序異常","Program exception");

    private String cn_tw;
    private String cn_sf;
    private String en_ac;

    private InternationalizationEnum(String cn_sf,String cn_tw,String en_ac){
        this.cn_tw=cn_tw;
        this.cn_sf=cn_sf;
        this.en_ac=en_ac;
    }
    /**
     * 获取繁体中文
     * @return
     */
    public String getCn_tw() {
        return cn_tw;
    }

    /**
     * 获取简体中文
     * @return
     */
    public String getCn_sf() {
        return cn_sf;
    }

    /**
     * 获取英文
     * @return
     */
    public String getEn_ac() {
        return en_ac;
    }

    /**
     * 获取头部语言类型languageType
     * 文本类型 1=简体中文 2=繁体中文 3=英文
     * @param request
     * @return
     */
    public String getMsg(HttpServletRequest request){
        String languageType=request.getHeader("languageType");
        if(StringUtils.isEmpty(languageType)){
            languageType="1";
        }
        if(languageType.equals("1")){
            return cn_sf;
        }
        if(languageType.equals("2")){
            return cn_tw;
        }
        if(languageType.equals("3")){
            return en_ac;
        }
        return cn_sf;
    }
}
