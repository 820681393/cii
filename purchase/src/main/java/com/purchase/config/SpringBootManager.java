package com.purchase.config;

import com.purchase.enums.EnvironmentalEnum;
import com.purchase.utils.MyDateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by Miracle on 2020/8/12.
 */
@EnableAutoConfiguration
public class SpringBootManager {

    /**
     *
     * @param clazz spring boot 启动类class
     * @param port 启动端口
     * @param environmentalType 启动环境 默认启动开发环境(DEV)
     * @param args
     */
    public static void run(Class<?> clazz, int port, EnvironmentalEnum environmentalType, String... args) throws ParseException {
        System.setProperty("server.port", port + "");
        System.setProperty("spring.profiles.active", environmentalType.getValue());
        SpringApplication.run(clazz, args);
        System.out.println(MyDateUtil.getPatternToString(new Date())+"==========> env = " + environmentalType.getValue());
        System.out.println(MyDateUtil.getPatternToString(new Date())+"==========> port = " + port);
    }

}
