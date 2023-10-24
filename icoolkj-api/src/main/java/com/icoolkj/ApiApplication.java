package com.icoolkj;

import com.icoolkj.api.wrap.boot.annotation.EnableApiWrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableApiWrap
public class ApiApplication
{
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
