package com.study.aes.commons;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author 真香
 * @Date 2021/3/22 17:08
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "spring.encrypt")
@Component
public class EncryptProperties {
    private final static String DEFAULT_KEY = "www.itboyhub.com";
    private String key = DEFAULT_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
