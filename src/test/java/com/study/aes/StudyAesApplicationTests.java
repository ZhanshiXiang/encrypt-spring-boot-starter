package com.study.aes;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.study.aes.util.GenerateSignatureUtil;
import com.study.aes.util.HttpClientUtil;
import com.study.idgenerator.contract.IdGeneratorOptions;
import com.study.idgenerator.idgen.YitIdHelper;
import com.study.webopenapi.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
@Slf4j
class StudyAesApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {

            // 全局初始化设置WorkerId，默认最大2^16-1。（初始化过程全局只需一次，且必须最先设置）
            IdGeneratorOptions options = new IdGeneratorOptions();
            options.WorkerId = 1;
            YitIdHelper.setIdGenerator(options);

// 初始化以后，就可以在需要的地方调用方法生成ID。
            long newId = YitIdHelper.nextId();
            System.out.println(newId);
        }



    @Test
    public void testHttpDoPost() {
        Map<String, String> params = new ConcurrentHashMap<>(10);
        String secret = "1ae41230bd1b4383a44f1b114ceba13c";
        params.put("partnerkey","6ee781ae6ef4496a");
        Long timesTamp = System.currentTimeMillis()/1000;
        System.out.println("time ==" + timesTamp);
        params.put("timestamp",String.valueOf(timesTamp));
        params.put("nonce",UUIDUtil.getUuid());
        params.put("secret",secret);
        params.put("vendorOrderNumber", UUIDUtil.getUuid());
        params.put("orderType","1");
        params.put("chargingStandard","0");
        params.put("activityID","0");
        params.put("createTime",getDateTimeString(new Date()));
        params.put("orderTime",getDateTimeString(new Date()));
        params.put("memberPhone","17601415583");
        params.put("memberName","广东保易-中华测试");
        params.put("startAddress","广西壮族自治区桂林市叠彩区桂林北站");
        params.put("startLatitude","253295.31505");
        params.put("startLongitude","1103017.24662");
        params.put("endAddress","");
        params.put("endLatitude","");
        params.put("endLongitude","");
        params.put("remark","测试勿接单，不用安排，不用打电话，辛苦了哟");
       // params.put("price","0");
        String signature = GenerateSignatureUtil.generateSignature(params, secret);
        System.out.println("sign = " + signature);
        params.put("sign",signature);
        log.info("开始捷顿代驾接口==========:{}",params);
        //String result = HttpClientUtil.doPost("https://dev.baoyitech.com.cn/driving/tDrivingOrderApi/getOrderInterface", params);
        String result = HttpClientUtil.doPost("https://mp.baoyitech.com.cn/driving/tDrivingOrderApi/getOrderInterface", params);
         System.out.println(result);
        }

    @Test
    public void testOpenSign() {
        Map<String, String> params = new ConcurrentHashMap<>(10);
        String secret = "1ae41230bd1b4383a44f1b114ceba13c";
        Long timesTamp = System.currentTimeMillis()/1000;
        System.out.println("time ==" + timesTamp);
        params.put("timestamp",String.valueOf(timesTamp));
        params.put("nonce",UUIDUtil.getUuid());
        params.put("secret",secret);
        params.put("name", "张三");
        params.put("address","中国");
        params.put("sex","0");
        // 调用MD5算法加密生成签名
        String signature = GenerateSignatureUtil.generateSignature(params, secret);
        System.out.println("sign = " + signature);
        // 签名加入请求参数
        params.put("sign",signature);
        log.info("开始请求open-sign接口==========:{}",params);
        String result = HttpClientUtil.doPost("http://localhost:9999/user/add", params);
        System.out.println(result);
    }



    @Test
        public void testCallbackLongandlat(){

            // 推送经纬度实时接口
            Map<String,String> map = new HashMap<String,String>();
            map.put("workId","CIC201711221526157470");
            map.put("orderId","3213123123");
            map.put("longitude","140.222");
            map.put("latitude","31.131");
            map.put("servicePerName","张三");
            map.put("servicePerPhone","13453912345");
            String jsonString= JSONObject.toJSONString(map);
            //System.setProperty("https.protocols", "TLSv1");
            String  response = HttpClientUtil.doPostJson("https://stcustservice.cic.cn:7103/avs/callback/longandlat", jsonString);
            System.out.println(response);
        }

        @Test
        public void testHttp(){

            Map<String,String> map = new HashMap<String,String>();
            map.put("timestamp","1617187972");
            map.put("nonce","62a439ab5d4e433c897f4b459181b2b3");
            map.put("secret","dasdafasbfhb3e34j3jb23b2jb342bjb32");
            map.put("companyName","杭州阿里巴巴科技有限公司");
            map.put("dockingPeople","张勇");
            map.put("mobilePhone","15423456789");
            map.put("partnerkey","f2h23usw32a232r4");
            map.put("sign","BEF2B375A108532D77269627CD6E6F38");
            String jsonString= JSONObject.toJSONString(map);
            String result = HttpClientUtil.doPostJson("http://localhost:8155/driving/drivingCooperation/testHttp", jsonString);
            System.out.println(result);

        }



       @Test
       public void testFormUrlencoded(){
           Map<String, String> params = new ConcurrentHashMap<>(10);
           params.put("channelID","100312");
           params.put("vendorOrderNumber","a1b5a7074ef94645a2a2e2cad3c97532");
           params.put("orderNumber","3217192");
           params.put("status","502");
           params.put("updateTime","2021-05-17 15:44:01");
           params.put("sign","1085efd5a742ef4e75bc3900698c7623");
           //String s = HttpClientUtil.doPost("https://dev.baoyitech.com.cn/driving/callback/orderStatusCallBack", params);
           //String s = HttpClientUtil.doPost("http://localhost:8155/driving/callback/orderStatusCallBack", params);
           String s = HttpClientUtil.doPost("https://mp.baoyitech.com.cn/driving/callback/orderStatusCallBack", params);
           System.out.println(s);
       }

        @Test
        public void testParse(){

            Map<String, Object> map = new HashMap<String, Object>();
            List<Object> list = new ArrayList<Object>();
            list.add("aa");
            list.add(123);
            list.add(new Object[] {"qq", 11});
            map.put("list", list);
            Map<String, Object> childMap = new HashMap<String, Object>();
            childMap.put("num", 78);
            childMap.put("str", "str?!+-str");
            childMap.put("array", new int[] {44,55});
            map.put("childMap", childMap);
            map.put("array", new String[] {"ss", "Kk"});
            System.out.println(map);
            String encodeStr = urlencode(map, "");
            System.out.println("encodeStr===="+encodeStr);
            String body = "mobilePhone=15423456789&partnerkey=f2h23usw32a232r4&companyName=%E6%9D%AD%E5%B7%9E%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4%E7%A7%91%E6%8A%80%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8&sign=918F0030E5531045D3E9C26DB1DA8FB7&dockingPeople=%E5%BC%A0%E5%8B%87&secret=dasdafasbfhb3e34j3jb23b2jb342bjb32&nonce=62a439ab5d4e433c897f4b459181b2b3&timestamp=1616751601";
            Map<String, Object> urldecode = urldecode(body);
            System.out.println("decodeObj===="+urldecode);
        }

     @Test
     public void verificationSign() {

        String body = "mobilePhone=15423456789&partnerkey=f2h23usw32a232r4&companyName=杭州阿里巴巴科技有限公司&sign=4C8DC0E4DF7C5740449AD68308510FAC&dockingPeople=张勇&secret=dasdafasbfhb3e34j3jb23b2jb342bjb32&nonce=62a439ab5d4e433c897f4b459181b2b3&timestamp=1616997938";
         Map<String,String> data = new TreeMap<>();
         if (StringUtils.isNotEmpty(body)) {
             // 用“ & ” 对body 进行分割
             String[] split = body.split("&");
             for (String s : split) {
                 String[] array = s.split("=");
                 for (int i = 0; i < array.length-1; i++) {
                     System.out.println("i======"+i);
                     System.out.println("array length ="+ array.length);
                     data.put(array[i],array[++i]);
                 }
             }
         }
     }


     @Test
     public void testConversion(){
         String body = "mobilePhone=15423456789&partnerkey=f2h23usw32a232r4&companyName=杭州阿里巴巴科技有限公司&sign=4C8DC0E4DF7C5740449AD68308510FAC&dockingPeople=张勇&secret=dasdafasbfhb3e34j3jb23b2jb342bjb32&nonce=62a439ab5d4e433c897f4b459181b2b3&timestamp=1616997938";
         Map<String,Object> data = new TreeMap<>();

                 Gson gson = new Gson();
                 data = gson.fromJson(body, data.getClass());//jsonStr需要转换的值
         System.out.println(data);
     }


     @Test
     public void testBoolean(){
        boolean flag = true;
        if (flag == false) {
            System.out.println("++++++++");
            System.out.println("存数据库");
        } else  {
            System.out.println("--------");
            System.out.println("不存库 直接返回给中华联合");
        }

     }





    public static String urlencode(Object params, String key) {
        String res = "";
        if(params instanceof Map) {
            Map<String, Object> _params = (Map<String, Object>) params;
            for(String i : _params.keySet()) {
                String k = key.isEmpty() ? i : (key +"["+ i +"]");
                String encodeValue = urlencode(_params.get(i), k);
                if(!encodeValue.isEmpty()) {
                    res += '&'+ encodeValue;
                }
            }
        }
        else if(params instanceof List) {
            List<Object> _params = (List<Object>) params;
            for(Integer i = 0; i < _params.size(); i++) {
                String k = key.isEmpty() ? i.toString() : (key +"["+ i.toString() +"]");
                String encodeValue = urlencode(_params.get(i), k);
                if(!encodeValue.isEmpty()) {
                    res += '&'+ encodeValue;
                }
            }
        }
        else if(params.getClass().isArray()) {
            Object[] _params;
            if(params instanceof Object[]) {
                _params = (Object[]) params;
            }
            else if(params instanceof String[]) {
                _params = (String[]) params;
            }
            else if(params instanceof int[]) {
                _params = ArrayUtils.toObject((int[]) params);
            }
            else if(params instanceof double[]) {
                _params = ArrayUtils.toObject((double[]) params);
            }
            else {
                _params = new Object[] {};
            }
            for(Integer i = 0; i < _params.length; i++) {
                String k = key.isEmpty() ? i.toString() : (key +"["+ i.toString() +"]");
                String encodeValue = urlencode(_params[i], k);
                if(!encodeValue.isEmpty()) {
                    res += '&'+ encodeValue;
                }
            }
        }
        else if(params instanceof String) {
            String _params = (String) params;
            try {
                res += '&'+ URLEncoder.encode(key, "UTF-8") +'='+ URLEncoder.encode(_params, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        else if(params instanceof Number) {
            Number _params = (Number) params;
            try {
                res += '&'+ URLEncoder.encode(key, "UTF-8") +'='+ URLEncoder.encode(_params.toString(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        else {
            return "";
        }
        return res.substring(1);
    }


    public static Map<String, Object> urldecode(String param) {
        if(param == null || param.isEmpty()) {
            return null;
        }
        //解码
        String[] params = param.split("&");
        Map<String, String> key2value = new TreeMap<String, String>();
        for(int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if(p.length == 0) {
                continue;
            }
            try {
                String keyStr = URLDecoder.decode(p[0], "UTF-8");
                if(keyStr.isEmpty()) {
                    continue;
                }
                String valueStr;
                if(p.length == 2) {
                    valueStr = URLDecoder.decode(p[1], "UTF-8");
                } else {
                    valueStr = "";
                }
                key2value.put(keyStr, valueStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        //遍历每一行传参
        Map<String, Object> map = new HashMap<String, Object>();
        for(Map.Entry<String, String> entry : key2value.entrySet()) {
            String keyStr = entry.getKey();
            String value = entry.getValue();
            //根目录的key
            Matcher keyMatcher = Pattern.compile("^[a-zA-Z\\_]{1}[\\w]*").matcher(keyStr);
            if(!keyMatcher.find()) {
                continue;
            }
            String key = keyMatcher.group(0);
            if(!map.containsKey(key)) {
                map.put(key, new HashMap<String, Object>());
            }

            //二级以及二级目录以上的key
            String pattern = "\\[([\\w]+?)\\]";
            Matcher filterMatcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(keyStr);
            //获取所有的patternKey
            List<String> patternKeyList = new ArrayList<String>();
            while(filterMatcher.find()) {
                String patternKey = filterMatcher.group(1);
                patternKeyList.add(patternKey);
            }
            //有子元素
            if(!patternKeyList.isEmpty()) {
                //遍历并写入
                Object childMap = map.get(key);
                int patternKeyListSize = patternKeyList.size();
                for(int j = 0; j < patternKeyListSize; j++) {
                    String patternKey = patternKeyList.get(j);
                    Map<String, Object> _childMap = (HashMap<String, Object>) childMap;
                    if(!_childMap.containsKey(patternKey)) {
                        //是否是最后一个节点，是的话直接赋值
                        if(j == patternKeyListSize-1) {
                            _childMap.put(patternKey, value);
                            break;
                        }
                        _childMap.put(patternKey, new HashMap<String, Object>());
                    }
                    childMap = _childMap.get(patternKey);
                }
            }
            //只有一级元素
            else {
                map.put(key, value);
            }
        }
        map = (Map<String, Object>) map2list(map);
        return map;
    }

    private static Object map2list(Map<String, Object> map) {
        Set<String> keySet = map.keySet();
        boolean all_is_number = true;
        for(String key : keySet) {
            //不是数字
            if(!Pattern.matches("^[0-9]+$", key)) {
                all_is_number = false;
            }
            Object childNode = map.get(key);
            if(childNode instanceof Map) {
                childNode = map2list((Map<String, Object>) childNode);
                map.put(key, childNode);
            }
        }
        Object res;
        if(all_is_number) {
            res = new ArrayList<Object>();
            for(String key : keySet) {
                Object value = map.get(key);
                ((List<Object>) res).add(value);
            }
        } else {
            res = map;
        }
        return res;
    }


    @Test
    public void testDateParse(){

        String dateTimeString = getDateTimeString(new Date());
        System.out.println(dateTimeString);
    }

    public static String getDateTimeString(Date date){
        if (date == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = simpleDateFormat.format(date);
        return dateString;
    }

    }
