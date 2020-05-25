package com.zstu.ky.kyykt.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.zstu.ky.kyykt.demo")).paths(PathSelectors.any()).build();// com为当前包路径
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("科艺云课堂-SimHash模块-API文档")// 页面标题
                .contact(new Contact("Maasteer", "", "763759021@qq.com"))// 作者
                .version("1.0")
                .description("swagger测试").build();
    }
}
