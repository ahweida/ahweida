package com.weida.epcommon.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
/**
 * @Auther: zhaof
 * @Date: 2021/3/5 9:31
 * @Desc: TODO
 */
@Slf4j
public class AesUtil {

    public static String encrypt(String input, String key){
        byte[] crypted = null;
        try{
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        }catch(Exception e){
            log.warn(e.toString());
            return null;
        }
        return new String(Base64.encodeBase64(crypted));
    }

    public static String decrypt(String input, String key){
        byte[] output = null;
        try{
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Base64.decodeBase64(input));
        }catch(Exception e){
            log.warn(e.toString());
            return null;
        }
        return new String(output);
    }

    public static void main(String[] args) {
        String weida = encrypt("fly", "ahhxhbkjtltxywxm");
        System.out.println(weida);

        String anhuihengxia1234 = decrypt(weida, "ahhxhbkjtltxywxm");
        System.out.println(anhuihengxia1234);
    }

}
