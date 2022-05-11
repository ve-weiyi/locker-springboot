package com.ve.locker.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2配置 自动生成接口文档
 * https://zhuanlan.zhihu.com/p/98075551
 *
 * Knife4j的开发文档
 * https://xiaoym.gitee.io/knife4j/documentation/selfdocument.html
 *
 * @author weiyi
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
public class Knife4jConfiguration {

    // 增强功能必须yml 和 该扩展配合使用，否则无法生效
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public Knife4jConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    private final String GROUP_NAME = "locker-2.0版本";

    @Bean(value = "defaultApi2")
    public Docket createRestApi() {
        // 添加自定义文档要对应 knife4j 版本选择 doc 类型， 否则该功能不生效
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //设置ip和端口，或者域名
                //.host("localhost:8080")
//                .groupName(GROUP_NAME)
                //启动用于api选择的生成器
                .select()
                //为有@Api注解的Controller生成API文档
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //为有@ApiOperation注解的方法生成API文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //为任何接口生成API文档
//                .apis(RequestHandlerSelectors.any())
                //为当前包下controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.ve.locker.controller"))
                .paths(PathSelectors.any())
                .build()
                //赋予插件体系
                .extensions(openApiExtensionResolver.buildExtensions(GROUP_NAME))
                //添加登录认证
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * 将api的元信息设置为包含在json resourcelisting响应中
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact=new Contact("huster",
                "https://ve77.cn:8084/locker/api/doc.html",
                "791422171@qq.com");
        //设置文档信息
        return new ApiInfoBuilder()
                .title("locker储物柜接口文档")
                .description("一款面向Android手机的个人隐私信息保密APP软件，实现个人银行账户及密码、邮件地址及密码、好友联系信息、web应用系统账户及密码等个人隐私信息的加密保存。")
                .contact(contact)
                .version("1.0")
                //.license("")//更新此API的许可证信息
                //.licenseUrl("")//更新此API的许可证Url
                .termsOfServiceUrl("https://ve77.cn/blog")//更新服务条款URL
                .build();
    }

    /**
     * 给API文档接口添加安全认证
     * @return
     */
    private List<SecurityScheme> securitySchemes(){
        List<SecurityScheme> securitySchemes=new ArrayList<>();
        //设置请求头信息
        ApiKey apiKey = new ApiKey("Authorization","Authorization","header");
        securitySchemes.add(apiKey);
        return securitySchemes;
    }

    private List<SecurityContext> securityContexts() {
        //设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("^(?!auth).*$"));
        return result;
    }
    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }
    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope
                ("global",
                        "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization",authorizationScopes));
        return result;
    }
}