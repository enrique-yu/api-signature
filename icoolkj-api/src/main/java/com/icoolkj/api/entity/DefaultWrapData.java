package com.icoolkj.api.entity;

import com.icoolkj.api.wrap.core.WrapData;
import com.icoolkj.api.wrap.core.annotation.SignIgnore;

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
