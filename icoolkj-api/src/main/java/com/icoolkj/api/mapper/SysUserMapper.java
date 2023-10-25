package com.icoolkj.api.mapper;

import com.icoolkj.api.entity.SysUser;

/**
 * 用户表 数据层
 *
 * @author icoolkj
 */
public interface SysUserMapper
{
    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);

}
