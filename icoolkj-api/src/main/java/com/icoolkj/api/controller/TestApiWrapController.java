package com.icoolkj.api.controller;

import com.icoolkj.api.annotation.Log;
import com.icoolkj.api.annotation.RateLimiter;
import com.icoolkj.api.entity.DefaultWrapData;
import com.icoolkj.api.entity.SysUser;
import com.icoolkj.api.enums.BusinessType;
import com.icoolkj.api.handler.CustomWrapHandler;
import com.icoolkj.api.service.SysUserService;
import com.icoolkj.api.utils.ResponseMessage;
import com.icoolkj.api.utils.ResponseMessageUtils;
import com.icoolkj.api.utils.bean.BeanValidators;
import com.icoolkj.api.wrap.boot.annotation.ApiWrap;
import com.icoolkj.api.wrap.core.WrapRequest;
import com.icoolkj.api.wrap.core.utils.AESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validator;

@RestController
@RequestMapping("/api/wrap/test")
public class TestApiWrapController
{
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private Validator validator;

    @Autowired
    private CustomWrapHandler customWrapHandler;

    @ApiWrap
    @Log(title = "testDefaultApiWrap日志", businessType = BusinessType.INSERT)
    @PostMapping("/testDefaultApiWrap")
    public ResponseMessage testDefaultApiWrap(@RequestBody WrapRequest<DefaultWrapData> wrapRequest){
        return ResponseMessageUtils.success();
    }

    @RateLimiter(time = 100, count = 1)
    @ApiWrap(value = CustomWrapHandler.class)
    @Log(title = "testCustomApiWrap日志", businessType = BusinessType.INSERT)
    @PostMapping("/testCustomApiWrap")
    public ResponseMessage testCustomApiWrap(@RequestBody WrapRequest<DefaultWrapData> wrapRequest){
        return ResponseMessageUtils.success();
    }

    @ApiWrap(value = CustomWrapHandler.class)
    @Log(title = "testUserInsert", businessType = BusinessType.INSERT)
    @PostMapping( "/testUserInsert")
    public ResponseMessage testUserInsert(@RequestBody WrapRequest<SysUser> wrapRequest){
        sysUserService.insertUser(wrapRequest.getData());
        return ResponseMessageUtils.success();
    }

    @ApiWrap(value = CustomWrapHandler.class)
    @Log(title = "testUserUpdate", businessType = BusinessType.INSERT)
    @PostMapping( "/testUserUpdate")
    public ResponseMessage testUserUpdate(@RequestBody WrapRequest<SysUser> wrapRequest){
        sysUserService.updateUser(wrapRequest.getData());
        return ResponseMessageUtils.success();
    }

    @ApiWrap(value = CustomWrapHandler.class)
    @Log(title = "testBindingResult", businessType = BusinessType.INSERT)
    @PostMapping( "/testBindingResult")
    public ResponseMessage testBindingResult(@RequestBody WrapRequest<SysUser> wrapRequest){
        String appSecret = customWrapHandler.getAppSecret(wrapRequest.getAppKey());
        SysUser sysUser = wrapRequest.getData();
        // 敏感数据解密进行校验
        sysUser.setEmail(AESUtils.decrypt(sysUser.getEmail(), appSecret));
        sysUser.setPhonenumber(AESUtils.decrypt(sysUser.getPhonenumber(), appSecret));
        sysUser.setPassword(AESUtils.decrypt(sysUser.getPassword(), appSecret));
        // bean对象属性验证
        BeanValidators.validateWithException(validator, sysUser);

        sysUserService.updateUser(sysUser);
        return ResponseMessageUtils.success();
    }

}
