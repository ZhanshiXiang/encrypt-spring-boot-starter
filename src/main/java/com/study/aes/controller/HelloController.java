package com.study.aes.controller;

import com.study.aes.annotation.Decrypt;
import com.study.aes.annotation.Encrypt;
import com.study.aes.commons.RespBean;
import com.study.aes.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 真香
 * @Date 2021/3/22 17:29
 * @Version 1.0
 */

@RestController
@RequestMapping("/aes")
public class HelloController {

    @GetMapping("/user")
    @Encrypt
    public RespBean getUser() {
        User user = new User();
        user.setId((long) 99);
        user.setUsername("好吃的副本");
        System.out.println("执行加密------");
        return RespBean.ok("ok", user);
    }

    @PostMapping("/user")
    public RespBean addUser(@RequestBody @Decrypt User user) {
        System.out.println("user = " + user);
        System.out.println("执行解密------");
        return RespBean.ok("ok", user);
    }

    @PostMapping("/test")
    public String test (User user) {

        return "Hello world";
    }

}
