package com.icoolkj.api.wrap.web.entity;

import com.icoolkj.api.wrap.core.annotation.SignIgnore;
import com.icoolkj.api.wrap.core.WrapData;

public class DefaultWrapData extends WrapData {
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
