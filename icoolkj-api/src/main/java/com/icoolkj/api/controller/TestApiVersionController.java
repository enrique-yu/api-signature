package com.icoolkj.api.controller;

import com.icoolkj.api.wrap.boot.annotation.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiVersion
@RestController
@RequestMapping("/api/{version}/test")
public class TestApiVersionController
{
    @GetMapping
    public String testApiVersion01(@PathVariable String version){
        return "test01 : " + version;
    }

    @GetMapping
    @ApiVersion(2)
    public String testApiVersion02(@PathVariable String version){
        return "test02 : " + version;
    }

}
