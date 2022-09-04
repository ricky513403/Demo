package com.demo.test;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//測試Jasypt加密功能
@SpringBootTest
public class JasyptTests {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void testSecret(){
        //加密
        String secret = stringEncryptor.encrypt("root");
        System.out.println(secret);

        //解密
        String decrypt = stringEncryptor.decrypt(secret);
        System.out.println(decrypt);
    }
}
