package com.icoolkj.api.wrap.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.Method;
import com.alibaba.fastjson2.JSON;
import com.icoolkj.api.wrap.client.WrapClient;
import com.icoolkj.api.wrap.client.utils.ImageUtils;
import com.icoolkj.api.wrap.client.utils.http.HttpUtils;
import com.icoolkj.api.wrap.core.WrapRequest;
import com.icoolkj.api.wrap.core.utils.AESUtils;
import com.icoolkj.api.wrap.web.entity.DefaultWrapData;
import com.icoolkj.api.wrap.web.entity.SysUser;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

public class WrapUtilsTest {

    public static final String appKey ="icoolkj";
    public static final String appSecret ="icoolkj";

    @Test
    public void testDefaultApiWrap() {
        DefaultWrapData wrapData = new DefaultWrapData();
        wrapData.setName("defaultWrapData默认处理请求测试");
        wrapData.setUrl("http://localhost:18080/api/wrap/test/testDefaultApiWrap");
        WrapClient wrapClient = WrapClient.create(appKey, appSecret);
        WrapRequest<DefaultWrapData> wrapDataWrapRequest = wrapClient.wrap(wrapData);
        System.out.println(JSON.toJSONString(wrapDataWrapRequest));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Connection", "false");
        HttpEntity<String> request = new HttpEntity(wrapDataWrapRequest, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:18080/api/wrap/test/testDefaultApiWrap", request, String.class );
        System.out.println(result.getBody());
    }

    @Test
    public void testCustomApiWrap() {
        DefaultWrapData wrapData = new DefaultWrapData();
        wrapData.setName("testCustomApiWrap自定义处理请求测试");
        wrapData.setUrl("http://localhost:18080/api/wrap/test/testCustomApiWrap");
        WrapClient wrapClient = WrapClient.create(appKey, appSecret);
        WrapRequest<DefaultWrapData> wrapDataWrapRequest = wrapClient.wrap(wrapData);
        System.out.println(JSON.toJSONString(wrapDataWrapRequest));

        String result = HttpUtils.request("http://localhost:18080/api/wrap/test/testCustomApiWrap", Method.POST.name(),
                wrapDataWrapRequest, null, 10000);

        System.out.println(result);
    }

    @Test
    public void testUserInsert() {
        SysUser sysUser = new SysUser();
        sysUser.setUserId("569865820228526088");
        sysUser.setDeptId("563785611970871296");
        sysUser.setUserName("icoolkj_zhangtao");
        sysUser.setNickName("张涛");
        sysUser.setEmail("zhangtao@qq.com");
        sysUser.setPhonenumber("15898989898");
        sysUser.setSex("男");
        sysUser.setAvatar("test");
        sysUser.setPassword("zhangtao123456");
        sysUser.setCreateBy("zhangtao");
        sysUser.setUpdateTime(new Date());
        WrapClient wrapClient = WrapClient.create(appKey, appSecret);
        WrapRequest<SysUser> wrapDataWrapRequest = wrapClient.wrap(sysUser);
        System.out.println(JSON.toJSONString(wrapDataWrapRequest));

        String result = HttpUtils.request("http://localhost:18080/api/wrap/test/testUserInsert", Method.POST.name(),
                wrapDataWrapRequest, null, 10000);

        System.out.println(result);
    }

    @Test
    public void updateUserInsert() {
        SysUser sysUser = new SysUser();
        sysUser.setUserId("569865820228526088");
        sysUser.setDeptId("563785611970871296");
        sysUser.setUserName("icoolkj_zhangtao");
        sysUser.setNickName("张涛");
        sysUser.setEmail("zhangtao@qq.com");
        sysUser.setPhonenumber("15898989898");
        sysUser.setSex("男");
        sysUser.setAvatar("test");
        sysUser.setPassword("zhangtao123456");
        sysUser.setUpdateBy("icoolkj_zhangtao");
        sysUser.setUpdateTime(new Date());
        WrapClient wrapClient = WrapClient.create(appKey, appSecret);
        WrapRequest<SysUser> wrapDataWrapRequest = wrapClient.wrap(sysUser);
        System.out.println(JSON.toJSONString(wrapDataWrapRequest));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Connection", "false");
        HttpEntity<String> request = new HttpEntity(wrapDataWrapRequest, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:18080/api/wrap/test/testUserUpdate", request, String.class );
        System.out.println(result.getBody());
    }

    @Test
    public void testBindingResult() {
        SysUser sysUser = new SysUser();
        sysUser.setUserId("569865820228526088");
        sysUser.setDeptId("563785611970871296");
        sysUser.setUserName("icoolkj_zhangtao");
        sysUser.setNickName("张涛");
        sysUser.setEmail(AESUtils.encrypt("zhangtao@qq.com", appSecret));
        sysUser.setPhonenumber(AESUtils.encrypt("15898989898", appSecret));
        sysUser.setSex("男");
        sysUser.setAvatar(Base64.encode(ImageUtils.localImageToBase64("src/main/resources/images/icoolkj.jpg")));
        sysUser.setPassword(AESUtils.encrypt("zhangtao123456", appSecret));
        sysUser.setUpdateBy("icoolkj_zhangtao");
        sysUser.setUpdateTime(new Date());
        WrapClient wrapClient = WrapClient.create(appKey, appSecret);
        WrapRequest<SysUser> wrapDataWrapRequest = wrapClient.wrap(sysUser);
        System.out.println(JSON.toJSONString(wrapDataWrapRequest));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Connection", "false");
        HttpEntity<String> request = new HttpEntity(wrapDataWrapRequest, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:18080/api/wrap/test/testBindingResult", request, String.class );
        System.out.println(result.getBody());
    }

}
