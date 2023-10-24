package com.icoolkj.api.entity;

/**
 * 第三方系统提供接口信息权限配置类
 *
 * @author icoolkj
 */
public class OpenApiAuthority extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String openApiId;

    /** 应用的唯一标识 */
    private String appId;

    /** 公匙（相当于账号） */
    private String appKey;

    /** 私匙（相当于密码） */
    private String appSecret;

    /** 删除标志（0代表存在 2代表删除） */
    private Integer delFlag;

    public String getOpenApiId() {
        return openApiId;
    }

    public void setOpenApiId(String openApiId) {
        this.openApiId = openApiId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "OpenApiAuthority{" +
                "openApiId='" + openApiId + '\'' +
                ", appId='" + appId + '\'' +
                ", appKey='" + appKey + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", delFlag=" + delFlag +
                '}';
    }
}
