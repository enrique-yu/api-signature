package com.icoolkj.api.controller;

import com.icoolkj.api.annotation.Log;
import com.icoolkj.api.annotation.RateLimiter;
import com.icoolkj.api.entity.DefaultWrapData;
import com.icoolkj.api.enums.BusinessType;
import com.icoolkj.api.handler.CustomWrapHandler;
import com.icoolkj.api.utils.AjaxResult;
import com.icoolkj.api.wrap.boot.annotation.ApiWrap;
import com.icoolkj.api.wrap.core.WrapRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wrap/test")
public class TestApiWrapController
{
    @ApiWrap
    @Log(title = "testDefaultApiWrap日志", businessType = BusinessType.INSERT)
    @PostMapping("/testDefaultApiWrap")
    public AjaxResult testDefaultApiWrap(@RequestBody WrapRequest<DefaultWrapData> wrapRequest){
        return AjaxResult.success();
    }

    @RateLimiter(time = 100, count = 1)
    @ApiWrap(value = CustomWrapHandler.class)
    @Log(title = "testCustomApiWrap日志", businessType = BusinessType.INSERT)
    @PostMapping("/testCustomApiWrap")
    public AjaxResult testCustomApiWrap(@RequestBody WrapRequest<DefaultWrapData> wrapRequest){
        return AjaxResult.success();
    }

}
