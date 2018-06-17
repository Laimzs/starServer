package com.starServer.service;

import com.starServer.entity.User;

/**
 * Created by zhangsong on 2018/1/1.
 */
public interface UserService {
    User getUserByPhone(String phone);
    int saveUser(User user);
    User getUserById(Integer id);
    int updateUser(User user);
}
