package com.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.doamin.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserDaoTestCase {

    @Autowired
    private UserDao userDao;

    @Test
    void testSave(){
        User user=new User();
        user.setUsername("username1");
        user.setPassword("password1");
        user.setRealname("username1");
        user.setGender(true);
        userDao.insert(user);
    }

    @Test
    void testDelete(){
        userDao.deleteById(2);
    }

    @Test
    void testUpdate(){
        User user = new User();
        user.setId(1);
        user.setUsername("修改帳號");
        user.setPassword("修改密碼");
        user.setRealname("修改真名");
        user.setGender(false);
        userDao.updateById(user);
    }

    @Test
    void testGetById(){
        userDao.selectById(1);
    }

    @Test
    void testGetAll(){
        userDao.selectList(null);
    }

    @Test
    void testGetPage(){
        IPage page=new Page(2,5);
        userDao.selectPage(page,null);
    }

    @Test
    void testGetBy(){
        LambdaQueryWrapper<User> lqw=new LambdaQueryWrapper<>();
        lqw.like(User::getUsername,"2");
        userDao.selectList(lqw);
    }

}
