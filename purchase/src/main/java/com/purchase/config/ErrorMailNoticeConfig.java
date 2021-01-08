package com.purchase.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.purchase.common.log.MyLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2020/5/5.
 */
@Component
public class ErrorMailNoticeConfig {


    @Autowired
    SystemConfig systemConfig;
    MyLogger myLogger=new MyLogger(ErrorMailNoticeConfig.class);

    /**
     * qq邮箱发送邮件
     * @throws Exception
     */
    public void sendAliMail(String error){
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", systemConfig.getMailAccessKey(), systemConfig.getMailAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            request.setAccountName(systemConfig.getMailAccountName());
            request.setFromAlias(systemConfig.getProjectName());
            request.setAddressType(1);
            request.setTagName(systemConfig.getMailTagName());
            request.setReplyToAddress(true);
            request.setToAddress(systemConfig.getMailToAddress());
            request.setSubject(systemConfig.getProjectName()+":"+simpleDateFormat.format(new Date()));
            //如果采用byte[].toString的方式的话请确保最终转换成utf-8的格式再放入htmlbody和textbody，若编码不一致则会被当成垃圾邮件。
            //注意：文本邮件的大小限制为3M，过大的文本会导致连接超时或413错误
            request.setHtmlBody(stringToHtml(error));
            request.setMethod(MethodType.POST);
            //如果调用成功，正常返回httpResponse；如果调用失败则抛出异常，需要在异常中捕获错误异常码；错误异常码请参考对应的API文档;
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (Exception e) {
            //捕获错误异常码
            myLogger.error(e);
        }
    }
    public  String stringToHtml(String info){
        info=info.replaceAll("\\n","<br/>");
        String[] infos=info.split("<br/>");
        String packageName = ErrorMailNoticeConfig.class.getPackage().getName();
        String packageInfo = packageName.substring(0,packageName.lastIndexOf("."));
        StringBuffer infoBuff=new StringBuffer();
        for (String s : infos) {
            if(s.indexOf(packageInfo)!=-1){
                infoBuff.append("<div style=\"color:blue\">");
                infoBuff.append(s);
                infoBuff.append("</div>");
            }else{
                infoBuff.append("<div style=\"color:red\">");
                infoBuff.append(s);
                infoBuff.append("</div>");
            }
        }
        return infoBuff.toString();
    }



}
