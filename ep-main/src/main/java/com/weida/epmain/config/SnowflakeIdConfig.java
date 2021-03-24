package com.weida.epmain.config;

import com.weida.epmain.util.InetAddressUtils;
import com.weida.epmain.util.SnowflakeUtil;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowflakeIdConfig {

    @Bean
    public SnowflakeUtil snowflakeUtil() {
        return new SnowflakeUtil(1, 1);

    }
}