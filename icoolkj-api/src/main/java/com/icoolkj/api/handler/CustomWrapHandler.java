package com.icoolkj.api.handler;


import cn.hutool.core.util.StrUtil;
import com.icoolkj.api.service.OpenApiAuthorityService;
import com.icoolkj.api.wrap.boot.ApiWrapProperties;
import com.icoolkj.api.wrap.boot.WrapHandler;
import com.icoolkj.api.wrap.boot.WrapStore;
import com.icoolkj.api.wrap.core.WrapData;
import com.icoolkj.api.wrap.core.WrapRequest;
import com.icoolkj.api.wrap.core.WrapSigner;
import com.icoolkj.api.wrap.core.exception.WrapReplayAttackException;
import com.icoolkj.api.wrap.core.exception.WrapTimestampException;
import com.icoolkj.api.wrap.core.exception.WrapUnauthorizedException;
import com.icoolkj.api.wrap.core.handler.DefaultWrapSigner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * API安全处理
 */
@Component
public class CustomWrapHandler implements WrapHandler
{
    private final Boolean enableSign;
    private final Long legalTime;
    private final String defaultSecret;
    private final WrapStore wrapStore;
    private final OpenApiAuthorityService openApiAuthorityService;

    public CustomWrapHandler(ApiWrapProperties apiWrapProperties,
                             WrapStore wrapStore,
                             OpenApiAuthorityService openApiAuthorityService) {

        this.enableSign = apiWrapProperties.getSign();
        this.legalTime = apiWrapProperties.getLegalTime() == null ? 300 : apiWrapProperties.getLegalTime();
        this.defaultSecret = apiWrapProperties.getSecret();
        this.wrapStore = wrapStore;
        this.openApiAuthorityService = openApiAuthorityService;
    }

    @Override
    public String getAppSecret(String appKey) {
        String secret = wrapStore.getSecret(appKey);
        if (StrUtil.isEmpty(secret)) {
            secret = openApiAuthorityService.selectOpenApiAuthorityByAppKey(appKey);
            if (StrUtil.isEmpty(secret)) {
                throw new WrapUnauthorizedException(String.format("appKey：%s,尚未授权，请联系管理员授权！", appKey));
            }
        }
        return secret;
    }

    @Override
    public boolean verifySignature(String appKey, WrapRequest<WrapData> request) {
        if (enableSign) {
            WrapSigner wrapSigner = new DefaultWrapSigner(getAppSecret(appKey));
            String signature = wrapSigner.signature(request);
            String signatureParam = request.getSignature();
            return signatureParam.equals(signature);
        } else {
            return true;
        }
    }

    @Override
    public void isLegalTime(long timestamp) {

        long diff = TimeUnit.MILLISECONDS.toSeconds(timestamp - System.currentTimeMillis());
        if (Math.abs(diff) > legalTime) {
            throw new WrapTimestampException("timestamp 校验错误!");
        }
    }

    @Override
    public void isReplayAttack(String appKey, long timestamp, int nonce, String signature) {

        String sign = wrapStore.getSign(appKey, timestamp, nonce);
        if (signature.equals(sign)) {
            throw new WrapReplayAttackException(String.format("重复的请求, appKey:%s, timestamp:%s, nonce:%s", appKey, timestamp, nonce));
        } else {
            wrapStore.putSign(appKey, timestamp, nonce, signature);
        }
    }
}
