package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.doamin.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTestCase {

    @Autowired
    private UserService userService;


    @Test
    void testSave(){
        User user= new User();
        user.setUsername("測試帳號名");
        user.setRealname("測試真名");
        user.setPassword("測試密碼");
        user.setGender(true);
        userService.save(user);
    }

    @Test
    void testDelete(){
        userService.removeById(1);
    }

    @Test
    void testUpdate(){
        User user = new User();
        user.setId(1);
        user.setUsername("測試新的帳號名");
        user.setRealname("測試新的真名");
        user.setPassword("測試新的密碼");
        user.setGender(false);
        userService.updateById(user);
    }

    @Test
    void testGetById(){
        System.out.println(userService.getById(4));
    }

    @Test
    void testGetAll(){
        userService.list();
    }

    @Test
    void testGetPage(){
        IPage<User> page = new Page<User>(0,5);
        userService.page(page);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
    }
}
