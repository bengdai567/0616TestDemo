package com.example.demo.util.jds;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    public AESUtil() {
    }

    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        } else if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        } else {
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            return (new Base64()).encodeToString(encrypted);
        }
    }

    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            } else if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            } else {
                byte[] raw = sKey.getBytes("utf-8");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(2, skeySpec);
                byte[] encrypted1 = (new Base64()).decode(sSrc);

                try {
                    byte[] original = cipher.doFinal(encrypted1);
                    return new String(original, "utf-8");
                } catch (Exception var7) {
                    System.out.println(var7.toString());
                    return null;
                }
            }
        } catch (Exception var8) {
            System.out.println(var8.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        String cKey = "sbsbsbsbsbsbsbsb";
        String cSrc = "";
        System.out.println(cSrc);
        String enString = Encrypt(cSrc, cKey);
        System.out.println("加密后的字串是：" + enString + ";长度为：" + enString.length());
        String DeString = Decrypt(enString, cKey);
        System.out.println(DeString);
        System.out.println("解密后的字串是：" + DeString + "---?" + !"null".equalsIgnoreCase(DeString) + "；长度为：" + DeString.length());
    }
}
