package com.moses.cloud;

import com.moses.cloud.commons.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author HanKeQi
 * @Date 2020/12/25 下午9:01
 * @Version 1.0
 **/
@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class SecurityApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SecurityApplication.class);
        try {
            ServerProperties serverProperties = run.getBean(ServerProperties.class);
            log.info("exec address http:/{}:{}"  , IpUtils.getLocalAddress0(), serverProperties.getPort());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
