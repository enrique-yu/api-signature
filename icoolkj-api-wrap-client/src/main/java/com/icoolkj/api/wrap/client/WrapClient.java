package com.icoolkj.api.wrap.client;

import com.icoolkj.api.wrap.core.WrapData;
import com.icoolkj.api.wrap.core.WrapRequest;
import com.icoolkj.api.wrap.core.WrapSigner;
import com.icoolkj.api.wrap.core.handler.DefaultWrapSigner;
import com.icoolkj.api.wrap.core.utils.NonceUtils;

/**
 * 包裹客户端，用于包装需要请求的对象
 *
 * @author linfeng
 */
public class WrapClient implements WrapOperation {

    private String appKey;
    private WrapSigner wrapSigner;

    private WrapClient(String appKey, String appSecret) {
        this.appKey = appKey;
        this.wrapSigner = new DefaultWrapSigner(appSecret);
    }

    @Override
    public <T extends WrapData> WrapRequest<T> wrap(T source) {

        WrapRequest<T> wrapRequest = new WrapRequest<>();
        wrapRequest.setData(source);
        wrapRequest.setAppKey(appKey);
        wrapRequest.setTimestamp(System.currentTimeMillis());
        wrapRequest.setNonce(NonceUtils.nonce());
        wrapRequest.setSignature(wrapSigner.signature(wrapRequest));

        return wrapRequest;
    }

    /**
     * 创建包裹客户端对象
     *
     * @param appKey    应用Key
     * @param appSecret 应用密钥
     * @return 客户端对象
     */
    public static WrapClient create(String appKey, String appSecret) {
        return new WrapClient(appKey, appSecret);
    }
}
