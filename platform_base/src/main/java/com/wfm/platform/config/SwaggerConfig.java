package com.wfm.platform.config;

import com.google.common.base.Predicates;
import com.wfm.platform.exception.StatusCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Weifengming
 * @description swagger配置类
 * @date 2020/2/3
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo initApiInfo() {
        return new ApiInfoBuilder()
                .title("Platform API Doc")
                .description("This is a restful api document of Platform.")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket createRestApi() {

        ParameterBuilder tokenPar = new ParameterBuilder();
        List pars = new ArrayList();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        pars.add(tokenPar.build());

        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(StatusCode.OK).message("请求成功").build());
        responseMessageList.add(new ResponseMessageBuilder().code(StatusCode.ERROR).message("请求失败").build());
        responseMessageList.add(new ResponseMessageBuilder().code(StatusCode.ACCESSERROR).message("权限不足").build());
        responseMessageList.add(new ResponseMessageBuilder().code(StatusCode.LOGINERROR).message("用户名或密码错误").build());
        responseMessageList.add(new ResponseMessageBuilder().code(StatusCode.REMOTEERROR).message("远程调用失败").build());
        responseMessageList.add(new ResponseMessageBuilder().code(StatusCode.REPERROR).message("重复操作").build());

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(initApiInfo())
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .select()
                .apis(RequestHandlerSelectors.any())
                //设置不显示错误接口地址
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .paths(PathSelectors.regex("/.*")).build()
                .globalOperationParameters(pars);
    }
}
