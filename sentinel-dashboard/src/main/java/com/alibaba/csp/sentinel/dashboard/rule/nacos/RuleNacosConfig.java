package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class RuleNacosConfig {

    @Bean
    public ConfigService nacosConfigService() throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, "127.0.0.1:8848");
         properties.put(PropertyKeyConst.NAMESPACE, "sentinel");
         properties.put(PropertyKeyConst.USERNAME, "nacos");
         properties.put(PropertyKeyConst.PASSWORD, "nacos");
        return ConfigFactory.createConfigService(properties);
    }

}
