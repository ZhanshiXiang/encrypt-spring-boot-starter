/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.study.webopenapi.utils;

import org.apache.commons.lang3.RandomUtils;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 
 * @date 2018-11-08
 */
public class UUIDUtil {

    private static ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    private static final AtomicInteger SEQ = new AtomicInteger(1000);

    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddHHmmssSS");


    public static String getUuid(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    /**
     * 生成时间戳
     *
     *
     * @return 20091418452329261000
     */
    public static String getOutIdCode(){

        LocalDateTime dataTime = LocalDateTime.now(ZONE_ID);
        if(SEQ.intValue()>9990){
            SEQ.getAndSet(1000);
        }
        return  dataTime.format(DF_FMT_PREFIX)+ getLocalIpSuffix()+SEQ.getAndIncrement();
    }

    private volatile static String IP_SUFFIX = null;
    private static String getLocalIpSuffix (){
        if(null != IP_SUFFIX){
            return IP_SUFFIX;
        }
        try {
            synchronized (UUIDUtil.class){
                if(null != IP_SUFFIX){
                    return IP_SUFFIX;
                }
                InetAddress addr = InetAddress.getLocalHost();
                //  172.17.0.4  172.17.0.199 ,
                String hostAddress = addr.getHostAddress();
                if (null != hostAddress && hostAddress.length() > 4) {
                    String ipSuffix = hostAddress.trim().split("\\.")[3];
                    if (ipSuffix.length() == 2) {
                        IP_SUFFIX = ipSuffix;
                        return IP_SUFFIX;
                    }
                    ipSuffix = "0" + ipSuffix;
                    IP_SUFFIX = ipSuffix.substring(ipSuffix.length() - 2);
                    return IP_SUFFIX;
                }
                IP_SUFFIX = RandomUtils.nextInt(10, 20) + "";
                return IP_SUFFIX;
            }
        }catch (Exception e){

            IP_SUFFIX =  RandomUtils.nextInt(10,20)+"";
            return IP_SUFFIX;
        }
    }
    
}
