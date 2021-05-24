package com.study.aes.util;

import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * MD5 sign签名校验宇生成工具
 */
public class GenerateSignatureUtil {

    public static final String FIELD_SIGN = "sign";

    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。
     *
     * @param data Map类型数据
     * @param key  API密钥
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data, String key) {
        if (!data.containsKey(FIELD_SIGN)) {
            return false;
        }
        String sign = data.get(FIELD_SIGN);
        return generateSignature(data, key).equals(sign);
    }

    public static String generateSignature(final Map<String, String> data, String key) {
        try {
            Set<String> keySet = data.keySet();
            String[] keyArray = keySet.toArray(new String[keySet.size()]);
            Arrays.sort(keyArray);
            StringBuilder sb = new StringBuilder();
            for (String k : keyArray) {
                if (k.equals(FIELD_SIGN)) {
                    continue;
                }
                if (data.get(k).trim().length() > 0)  {
                    // 参数值为空，则不参与签名
                    sb.append(k).append("=").append(data.get(k).trim()).append("&");
                }
            }
            sb.append("key=").append(key);
            System.out.println("签名前"+sb);
            return MD5.md5(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 专用于安代驾sign签名
     *
     * @param data
     * @param key
     * @return
     */
    public static String generateSignAnDriving(final Map<String, String> data, String key) {
        try {
            Set<String> keySet = data.keySet();
            String[] keyArray = keySet.toArray(new String[keySet.size()]);
            Arrays.sort(keyArray);
            StringBuilder sb = new StringBuilder();
            for (String k : keyArray) {
                if (k.equals(FIELD_SIGN)) {
                    continue;
                }
                if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                {
                    sb.append(k).append("=").append(data.get(k).trim()).append("&");
                }
            }
            sb.append("key=").append(key);
            return MD5.md5(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
      /*  Map<String, String> data = new HashMap<>();
        data.put("user_phone", "18802011391");
        data.put("coupon_code", "2167 3908 4433");
//        data.put("status","100");
        data.put("coupon_type", "A025");
        data.put("timestamp", "1531730432");
        data.put("partner_id", "84513315");
        data.put("sign", "3389ACEBB3AEC93DB89C91614D3CC1C0");
        String secret = "JYHS1yWXCW00ore71TXE4aDatAEN6j0G";
        System.out.println(generateSignature(data, secret));
        System.out.println("校验：" + isSignatureValid(data, secret));*/



    }



}
