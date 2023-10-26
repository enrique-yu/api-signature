package com.icoolkj.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icoolkj.api.wrap.core.WrapData;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 用户对象 SysUser
 *
 * @author icoolkj
 */
public class SysUser extends WrapData
{
    /** 用户ID */
    private String userId;

    /** 部门ID */
    private String deptId;

    /** 用户账号 */
    @NotBlank(message = "用户账号不能为空")
    private String userName;

    /** 用户昵称 */
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;

    /** 用户邮箱 */
    @NotBlank(message = "用户邮箱不能为空")
    @Size(min = 1, max = 30, message = "用户邮箱不能超过30个字符")
    @Email(message = "用户邮箱格式不正确")
    //@Pattern(regexp = "[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+", message = "用户邮箱格式不正确")
    private String email;

    /** 手机号码 */
    @NotBlank(message = "手机号码不能为空")
    @Size(min = 11, max = 11, message = "手机号码不能超过11个字符")
    @Pattern(regexp = "0?(13|14|15|17|18|19)[0-9]{9}", message = "手机号格式不正确")
    private String phonenumber;

    /** 用户性别 */
    @NotBlank(message = "用户性别不能为空")
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    @NotBlank(message = "密码不能为空")
    private String password;

    /** 帐号状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;

    /** 备注 */
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
