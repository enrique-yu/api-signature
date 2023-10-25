package com.icoolkj.api.service;

import com.icoolkj.api.entity.SysUser;
import com.icoolkj.api.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户 业务层处理
 *
 * @author icoolkj
 */
@Service
public class SysUserService
{

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user)
    {
        return userMapper.insertUser(user);
    }


    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user)
    {
        return userMapper.updateUser(user);
    }

}
