package com.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.contorller.UserController;
import com.demo.dao.UserDao;
import com.demo.doamin.User;
import com.demo.service.UserService;
import com.demo.utils.exception.PassWordNotCorrectException;
import com.demo.utils.exception.UserNameBeRegisteredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;


@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username, String password) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user=userDao.selectOne(wrapper);

        String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if(!user.getPassword().equals(passwordMD5)) {
            log.debug("帳號:"+username+" 密碼輸入錯誤");
            throw new PassWordNotCorrectException("密碼輸入錯誤");
        }
        return user;
    }

    @Override
    public void register(User user) {
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("username",user.getUsername());

        if(this.getBaseMapper().selectCount(queryWrapper)>0) throw new UserNameBeRegisteredException("當前用戶名已被註冊");
        //進行MD5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        this.saveUser(user);
    }

    @Override
    public boolean saveUser(User User) {
        return userDao.insert(User)>0;
    }

}
