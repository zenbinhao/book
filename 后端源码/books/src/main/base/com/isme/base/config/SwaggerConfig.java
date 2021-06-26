package com.isme.base.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger
public class SwaggerConfig {
    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig){
        this.springSwaggerConfig = springSwaggerConfig;
    }

    public ApiInfo apiInfo(){
        ApiInfo info = new ApiInfo("毕业项目api对接平台","提供接口","联系开发者:13387330595",
                "59419293@qq.com","","");
        return info;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation () {
        return  new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo());
    }
}
