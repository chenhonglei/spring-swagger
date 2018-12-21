package com.bai.swagger.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 * 遗留问题:
 * 1. @RequestParam使用后url发生错误.
 * 2. 返回简单对象时json格式有问题,框架出现的.
 * 3. 一些注解未加入,需要时参考pet的jar内代码
 * Created by zeyuphoenix on 16/7/28.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    // ================================================================
    // Constants
    // ================================================================

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfiguration.class);

    // ================================================================
    // Fields
    // ================================================================

    /**
     * 常见注解
     * @Api 用在类上，说明该类的作用
     * @ApiOperation 用在方法上，说明方法的作用
     * @ApiImplicitParams 用在方法上包含一组参数说明
     *   @ApiImplicitParam 用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
     *      paramType：参数放在哪个地方
     *          header--> 请求参数的获取：@RequestHeader
     *          query-->  请求参数的获取：@RequestParam
     *          path（用于restful接口）-->请求参数的获取：@PathVariable
     *          body（不常用）
     *          form（不常用）
     *      name：参数名
     *      dataType：参数类型
     *      required：参数是否必须传
     *      value：参数的意思
     *      defaultValue：参数的默认值
     *  @ApiResponses 用于表示一组响应
     *  @ApiResponse 用在@ApiResponses中，一般用于表达一个错误的响应信息
     *      code：数字，例如400
     *      message：信息，例如"请求参数没填好"
     *      response：抛出异常的类
     *  @ApiModel 描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
     *  @ApiModelProperty 描述一个model的属性
     */

    // ================================================================
    // Constructors
    // ================================================================

    // ================================================================
    // Methods from/for super Interfaces or SuperClass
    // ================================================================

    // ================================================================
    // Public or Protected Methods
    // ================================================================

    /**
     * docket 可以定义多个组，把不同的api进行分类
     */
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("小程序接口")
                // .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .enableUrlTemplating(true)
                .pathMapping("/")           // base，最终调用接口后会和paths拼接在一起
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bai.swagger.demo.controller"))
                .paths(PathSelectors.any()) //过滤的接口. 支持正则
                .build()
                .apiInfo(apiInfo());

    }

    // ================================================================
    // Getter & Setter
    // ================================================================

    // ================================================================
    // Private Methods
    // ================================================================

    /**
     * API版本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 大标题
                .title("swagger2构建restful api接口")
                // 描述,小标题
                .description("API 描述：Framework Rest Api, using spring boot、springMVC、spirngfox、swagger、shiro and so on.")
                .termsOfServiceUrl("http://www.longfor.net")
                // 作者、网站链接、邮箱信息
                .contact(new Contact("陈红磊", "http://www.longfor.net", "jidemeihao@163.com"))
                // 版本
                .version("2.0")
                .build();
    }

    // ================================================================
    // Inner or Anonymous Class
    // ================================================================

    // ================================================================
    // Test Methods
    // ================================================================

}
