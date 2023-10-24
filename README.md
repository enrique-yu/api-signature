# API签名验证工具

## 介绍
🎉 API签名验证工具，接口数据的安全问题。

在为第三方系统提供接口的时候，肯定要考虑接口数据的安全，例如：数据是**否被篡改、数据是否已经过时、数据是否可以重复提交**等问题。

## API签名验证流程
**客户端：**

1、创建包裹数据对象，对象可以继承WrapData，例如：DefaultWrapData；

2、创建包裹客户端对象，通过服务端提供的appKey和appSecret；

3、通过客户端对象包装需要请求的包裹数据对象，按照签名规则生成签名signature，产生API包裹请求对象WrapRequest；

4、以POST方式发送请求；

5、等待服务端响应

**服务端：**

1、通过ApiWrap注解进行客户端请求数据的签名校验

2、根据校验结果响应客户端。

# 服务端安全签名验签

## 引入jar包
### gradle
```
compile 'com.icoolkj:icoolkj-api-wrap-boot:{version}'
```
### maven
```
<dependency>
    <groupId>com.icoolkj</groupId>
    <artifactId>icoolkj-api-wrap-boot</artifactId>
    <version>{version}</version>
</dependency>
```

## 使用说明

- 使用EnableApiWrap注解，开启API Wrap功能
```java
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableApiWrap
public class ApiApplication
{
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
```

- 在controller类方法或类上添加@ApiWrap注解, 注解可以自定义API签名验签处理类（CustomWrapHandler.class），可以系统默认处理类；只有使用了注解的类和方法才能接收签名验签请求
```java
@RestController
@RequestMapping("/api/wrap/test")
public class TestApiWrapController
{
    // 默认API签名验签处理类
    @ApiWrap 
    @Log(title = "testDefaultApiWrap日志", businessType = BusinessType.INSERT)
    @PostMapping("/testDefaultApiWrap")
    public AjaxResult testDefaultApiWrap(@RequestBody WrapRequest<DefaultWrapData> wrapRequest){
        return AjaxResult.success();
    }

    // 自定义API签名验签处理类
    @ApiWrap(value = CustomWrapHandler.class)
    @Log(title = "testCustomApiWrap日志", businessType = BusinessType.INSERT)
    @PostMapping("/testCustomApiWrap")
    public AjaxResult testCustomApiWrap(@RequestBody WrapRequest<DefaultWrapData> wrapRequest){
        return AjaxResult.success();
    }
}
```

- 自定义API签名验签处理类 
```java
// CustomWrapHandler类需要实现WrapHandler接口
@Component
public class CustomWrapHandler implements WrapHandler
{
    private final Boolean enableSign;
    private final Long legalTime;
    private final String defaultSecret;
    private final WrapStore wrapStore;
    private final OpenApiAuthorityService openApiAuthorityService;
    //....
}
```

- WrapRequest<DefaultWrapData> 是统一的API验证请求类，其中泛型 DefaultWrapData类需要继承WrapData类
```java
public class WrapRequest<T extends WrapData> {

    private String appKey;
    private String signature;
    private long timestamp;
    private int nonce;
    private T data;
    //setter getter省略.... 
}
```

```java
import com.icoolkj.api.wrap.core.WrapData;
import com.icoolkj.api.wrap.core.annotation.SignIgnore;

public class DefaultWrapData extends WrapData {

    // SignIgnore 注解标识标识忽略属性用于签名
    @SignIgnore
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
```

- 存储appKey和appSecret用于请求验证
```
@Autoware
private WrapStore wrapStore;

// 存储密钥
wrapStore.putSecret(appKey, appSecret);

```

- 配置文件
```
#===========api wrap===========
api:
  wrap:
    legalTime: 300    # 单位秒，请求时间和服务器时间不能超过300秒
    secret: icoolkj   # app密钥，若用户没有自定义，则使用此
# 若没有redis配置，wrapStore为本地存储
# 若配置了redis，则wrapStore为redis存储
#===========api wrap redis=============
spring:
  redis:  # redis 配置
    host: 127.0.0.1  # 地址
    port: 16379  # 端口，默认为6379
    # 数据库索引
    database: 1
    # 密码
    password: redis123456
```
- 若没有redis配置，wrapStore为本地存储
- 若配置了redis，则wrapStore为redis存储


# 客户端辅助签名工具

## 引入工具包
### gradle
```
compile 'com.icoolkj:icoolkj-api-wrap-client:{version}'
```
### maven
```
<dependency>
    <groupId>com.icoolkj</groupId>
    <artifactId>icoolkj-api-wrap-client</artifactId>
    <version>{version}</version>
</dependency>
```
## 使用说明

```
DefaultWrapData wrapData = new DefaultWrapData();
wrapData.setName("defaultWrapData");
wrapData.setUrl("http://localhost:18080/api/wrap/test/testDefaultApiWrap");
WrapClient wrapClient = WrapClient.create("icoolkj", "icoolkj");
WrapRequest<DefaultWrapData> wrapDataWrapRequest = wrapClient.wrap(wrapData);
WrapClient wrapClient = WrapClient.create(appKey, appSecret);
WrapRequest<DefaultWrapData> request = wrapClient.wrap(WrapData)
// request 为带签名信息的对象
```

# 服务端扩展
## 扩展WrapStore

```java
@Service
class CustomWrapStore extends RedisWrapStore {
    
}
```
这样继承RedisWrapStore类或者实现WrapStore接口，可自定义包裹存储接口。系统自动使用你自定义的WrapStore

### 扩展WrapHandler
### 自定义CustomWrapHandler
```
// 自定义WrapHandler, 使用@Component注入到spring中管理
@Component
public class CustomWrapHandler implements WrapHandler {
    @Override
    public String getAppSecret(String appKey) {
        return null;
    }

    @Override
    public String getSignature(String appKey, WrapRequest<WrapData> request) {
        return null;
    }

    @Override
    public void isLegalTime(long timestamp) {

    }

    @Override
    public void isReplayAttack(String appKey, long timestamp, int nonce, String signature) {

    }
}
```
### 使用CustomWrapHandler
```
// ApiWrap中指定CustomWrapHandler.class类，则系统会根据此类型从Spring中获取对应的实例
    @ApiWrap(value = CustomWrapHandler.class)
    @PostMapping(value = "/web")
    public WrapRequest<DefaultWrapData> custom(@RequestBody WrapRequest<DefaultWrapData> request) {
        return request;
    }
```


