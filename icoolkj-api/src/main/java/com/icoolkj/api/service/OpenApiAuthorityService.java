package com.icoolkj.api.service;

import cn.hutool.core.util.StrUtil;
import com.icoolkj.api.entity.OpenApiAuthority;
import com.icoolkj.api.mapper.OpenApiAuthorityMapper;
import com.icoolkj.api.wrap.boot.WrapStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenApiAuthorityService
{
    @Autowired
    private OpenApiAuthorityMapper openApiAuthorityMapper;

    @Autowired
    private WrapStore wrapStore;

    public List<OpenApiAuthority> selectOpenApiAuthorityList(){
       return openApiAuthorityMapper.selectOpenApiAuthorityList();
    };

    public String selectOpenApiAuthorityByAppKey(String appKey){
       String appSecret = openApiAuthorityMapper.selectOpenApiAuthorityByAppKey(appKey);
        if (StrUtil.isNotEmpty(appSecret))
        {
            wrapStore.putSecret(appKey, appSecret);
        }
        return appSecret;
    };

}
