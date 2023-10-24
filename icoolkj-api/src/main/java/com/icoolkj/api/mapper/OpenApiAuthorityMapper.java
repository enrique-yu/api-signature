package com.icoolkj.api.mapper;

import com.icoolkj.api.entity.OpenApiAuthority;

import java.util.List;

public interface OpenApiAuthorityMapper
{
    List<OpenApiAuthority> selectOpenApiAuthorityList();

    String selectOpenApiAuthorityByAppKey(String appKey);
}
