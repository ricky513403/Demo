package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.doamin.User;


public interface UserService extends IService<User> {

    boolean saveUser(User user);

    void register(User user);

    User login(String username, String password);
}
