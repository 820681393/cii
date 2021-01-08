package com.purchase.config;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

/**
 * @author Miracle
 * @date 2020/12/10 0:25
 * 教程地址：
 * https://www.cnblogs.com/KillBugMe/archive/2004/01/13/13140226.html
 * 获取char id地址
 *  https://api.telegram.org/bot1456447083:AAGfKvTcZDak2O3rPDdmFbuOoAaUGN-03r8/getUpdates
 *
 */
public class MyTelegramBot {

    private static TelegramBot bot;

    public static boolean sendMsg(String msg){
        if(bot==null){
            bot= new TelegramBot("1456447083:AAGfKvTcZDak2O3rPDdmFbuOoAaUGN-03r8");
        }
        SendMessage request = new SendMessage("-428565990", msg);
        SendResponse sendResponse = bot.execute(request);
        return sendResponse.isOk();
    }

    public static String appendString(String info){
        String[] infos=info.split("\\n");
        String packageName = MyTelegramBot.class.getPackage().getName();
        String packageInfo = packageName.substring(0,packageName.lastIndexOf("."));
        StringBuffer infoBuff=new StringBuffer();
        for (String s : infos) {
            if(s.indexOf(packageInfo)!=-1){
                infoBuff.append(s);
                infoBuff.append("\n");
            }
        }
        return infoBuff.toString();
    }

    public static void main(String[] args) {
        sendMsg("你好吗");
    }
}
