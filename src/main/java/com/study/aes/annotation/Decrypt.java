package com.study.aes.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 真香
 * @Date 2021/3/22 17:04
 * @Version 1.0
 * 哪个接口/参数添加了 @Decrypt 注解就对哪个接口/参数进行解密。
 * @Decrypt 比 @Encrypt 多了一个使用场景就是 @Decrypt 可以用在参数上
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.PARAMETER})
public @interface Decrypt {
}
