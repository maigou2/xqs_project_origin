package com.xqs.commoncore.util;

import java.security.MessageDigest;

public class Md5Util {
    /**
     * 生成md5.
     */
    public static String getMd5(String str) {
        String md5 = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Byte = md.digest(str.getBytes());
            md5 = byteToHex(md5Byte);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * 转16进制
     */
    private static String byteToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if (num < 0) {
                num += 256;
            }
            if (num < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(num));
        }
        return sb.toString().toUpperCase();
    }
}
