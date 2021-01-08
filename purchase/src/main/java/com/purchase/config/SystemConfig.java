package com.purchase.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by ben on 2017-03-20 0020.
 */
@Component
@PropertySource("classpath:sysconfig.properties")
@Data
public class SystemConfig {

    @Value("${projectName}")
    public String projectName;

    @Value("${isShowSql}")
    public boolean isShowSql;

    @Value("${isMonitorSql}")
    public boolean isMonitorSql;

    @Value("${aliyunOos}")
    public String aliyunOos;

    @Value("${mailAccessKey}")
    public String mailAccessKey;

    @Value("${mailAccessSecret}")
    public String mailAccessSecret;

    @Value("${mailAccountName}")
    public String mailAccountName;

    @Value("${mailToAddress}")
    public String mailToAddress;

    @Value("${mailTagName}")
    public String mailTagName;


}
