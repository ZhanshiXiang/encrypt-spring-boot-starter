package com.study.aes.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 真香
 * @Date 2021/3/22 17:06
 * @Version 1.0
 *  哪个接口方法添加了 @Encrypt 注解就对哪个接口的数据加密返回，
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Encrypt {
}
