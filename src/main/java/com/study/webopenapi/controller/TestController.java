package com.study.webopenapi.controller;

import com.alibaba.fastjson.JSON;
import com.study.webopenapi.constant.AuthConstant;
import com.study.webopenapi.dto.TestDTO;
import com.study.webopenapi.response.BaseResponse;
import com.study.webopenapi.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试
 * @author gourd.hu
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {



    @GetMapping("getT")

    public BaseResponse testGet(@RequestParam String name){
        return BaseResponse.ok("Success");
    }

    @PostMapping("postT")

    public BaseResponse testPost(String name,String id){
        return BaseResponse.ok("Success");
    }

    @PostMapping("postT2")

    public BaseResponse testPost2(@RequestBody TestDTO testDTO){
        return BaseResponse.ok("Success");
    }



    @PostMapping("getSign")

    public String getSign(){
        Map<String, String> sParaTemp = new HashMap<>(16);
        sParaTemp.put(AuthConstant.NONCE_KEY, RandomUtils.nextLong()+"");
        sParaTemp.put(AuthConstant.ACCESS_KEY,"f2h23usw32a232r4");
        sParaTemp.put(AuthConstant.TIMESTAMP_KEY, String.valueOf(System.currentTimeMillis()/1000));
        log.info("生成签名的参数: ", JSON.toJSONString(sParaTemp));
        sParaTemp.put(AuthConstant.SECRET_KEY, "dasdafasbfhb3e34j3jb23b2jb342bjb32");
        String signStr = SignUtil.generateSign(sParaTemp,3000000);
        return signStr;
    }

}
