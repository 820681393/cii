package com.purchase;

import com.purchase.auto.MybatisPlusGeneratorUtils;
import com.purchase.config.SpringBootApplicationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Miracle
 * @date 2020/12/3 14:45
 */
@SpringBootTest
public class AutoCreateService {

    @Autowired
    SpringBootApplicationConfig springBootApplicationConfig;

    @Test
    public void MiracleAotuCreateTable() {
        MybatisPlusGeneratorUtils mybatisPlusGeneratorUtils=new MybatisPlusGeneratorUtils();
        mybatisPlusGeneratorUtils.init("Miracle",springBootApplicationConfig);
        mybatisPlusGeneratorUtils.createTableInfo("merchant_user_info");
    }

    @Test
    public void MusaAotuCreateTable() {
        MybatisPlusGeneratorUtils mybatisPlusGeneratorUtils=new MybatisPlusGeneratorUtils();
        mybatisPlusGeneratorUtils.init("Musa",springBootApplicationConfig);
        mybatisPlusGeneratorUtils.createTableInfo("merchant_deliver_info");
    }


}
