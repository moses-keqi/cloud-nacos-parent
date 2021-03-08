package com.moses.cloud.security.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author HanKeQi
 * @Date 2021/1/5 下午1:38
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("AGENT SECURITY SERVICE API").version("1.0.0").build();
    }

    @Bean
    public Docket securityManageApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("管理端API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.moses.cloud.security.controller.manage"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket securityWebApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("web端API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.moses.cloud.security.controller.web"))
                .paths(PathSelectors.any())
                .build();
    }

}
