package com.demo.test;

import com.demo.utils.VerifyCodeUtils;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

//驗證碼測試
public class VerifyCodeTests {

    @Test
    public void testGenerate() throws IOException {
        String s= VerifyCodeUtils.generateVerifyCode(4);
        System.out.println(s);
        //寫入圖片

        FileOutputStream os = new FileOutputStream("src/test/java/com/demo/test/picture/test.jpg");
        VerifyCodeUtils.outputImage(220,80,os,s);


    }
}
