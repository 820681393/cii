package com.purchase.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Miracle
 * @date 2020/12/3 0:39
 */
@Configuration
//@ConfigurationProperties(prefix = "jdbc")
@Data
public class SpringBootApplicationConfig {

    @Value("${spring.profiles.active}")
    private String springProfilesActive;


    @Value("${spring.datasource.url}")
    private String springDatasourceUrl;


    @Value("${spring.datasource.username}")
    private String springDatasourceUserName;


    @Value("${spring.datasource.password}")
    private String springDatasourcePassWord;

    @Value("${spring.datasource.driver-class-name}")
    private String springDatasourceDriverClassName;

}
