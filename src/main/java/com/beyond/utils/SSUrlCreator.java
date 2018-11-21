package com.beyond.utils;

import com.beyond.entity.SSConfigEntity;
import sun.misc.BASE64Encoder;

/**
 * @author beyondlov1
 * @date 2018/11/18
 */
public class SSUrlCreator {
    public static String create(SSConfigEntity ssConfigEntity){

        BASE64Encoder base64Encoder = new BASE64Encoder();
        String sb = ssConfigEntity.getEncryption() +
                ":" +
                ssConfigEntity.getPassword() +
                "@" +
                ssConfigEntity.getIp() +
                ":" +
                ssConfigEntity.getPort();
        byte[] bytes = sb.getBytes();
        String encode = base64Encoder.encode(bytes);

        return "ss://"+encode;
    }
}
