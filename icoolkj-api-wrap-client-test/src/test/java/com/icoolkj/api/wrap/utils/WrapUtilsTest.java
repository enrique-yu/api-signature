package com.icoolkj.api.wrap.utils;

import cn.hutool.http.Method;
import com.alibaba.fastjson2.JSON;
import com.icoolkj.api.wrap.client.WrapClient;
import com.icoolkj.api.wrap.client.utils.http.HttpUtils;
import com.icoolkj.api.wrap.core.WrapRequest;
import com.icoolkj.api.wrap.web.entity.DefaultWrapData;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WrapUtilsTest {

    @Test
    public void testDefaultApiWrap() {
        DefaultWrapData wrapData = new DefaultWrapData();
        wrapData.setName("defaultWrapData");
        wrapData.setUrl("http://localhost:18080/api/wrap/test/testDefaultApiWrap");
        WrapClient wrapClient = WrapClient.create("icoolkj", "icoolkj");
        WrapRequest<DefaultWrapData> wrapDataWrapRequest = wrapClient.wrap(wrapData);
        System.out.println(JSON.toJSONString(wrapDataWrapRequest));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Connection", "false");
        HttpEntity<String> request = new HttpEntity(wrapDataWrapRequest, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:18080/api/wrap/test/testDefaultApiWrap", request, String.class );
        System.out.println(result);
    }

    @Test
    public void testCustomApiWrap() {
        DefaultWrapData wrapData = new DefaultWrapData();
        wrapData.setName("defaultWrapData");
        wrapData.setUrl("http://localhost:18080/api/wrap/test/testCustomApiWrap");
        WrapClient wrapClient = WrapClient.create("icoolkj", "icoolkj");
        WrapRequest<DefaultWrapData> wrapDataWrapRequest = wrapClient.wrap(wrapData);
        System.out.println(JSON.toJSONString(wrapDataWrapRequest));

        String result = HttpUtils.request("http://localhost:18080/api/wrap/test/testCustomApiWrap", Method.POST.name(),
                wrapDataWrapRequest, null, 10000);

        System.out.println(result);
    }
}
