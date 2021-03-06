package com.bai.swagger.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Api("用户操作相关的接口")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @ApiOperation(value = "根据id查询学生信息", notes = "查询数据库中某个的学生信息")
    @ApiImplicitParam(name = "id", value = "学生ID", paramType = "path", required = true, dataType = "int")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getStudent(@PathVariable int id) {
        logger.info("开始查询某个学生信息");
        return "开始测试接口";
    }
}
