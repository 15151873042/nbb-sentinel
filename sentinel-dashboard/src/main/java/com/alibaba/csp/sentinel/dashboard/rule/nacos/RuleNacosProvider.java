package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RuleNacosProvider {

    @Autowired
    private ConfigService configService;

    public String getRules(String dataId, String app) {

        try {
            // 将服务名称设置为GroupId
            return configService.getConfig(dataId, app, 3000);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }
}
