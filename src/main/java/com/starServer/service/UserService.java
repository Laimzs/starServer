package com.starServer.service;

import com.starServer.entity.User;
import com.starServer.entity.response.BooleanResponse;
import com.starServer.entity.response.ResponseData;

import java.util.List;

/**
 * Created by zhangsong on 2018/1/1.
 */
public interface UserService {
    User getUserByPhone(String phone);
    ResponseData<List<User>>  getUserListByPage(int page,int pageSize);
    int saveUser(User user);
    User getUserById(Integer id);
    int updateUser(User user);
    BooleanResponse deleteUser(Integer id);
    ResponseData<List<User>>  getAllCancelUsers(int page,int pageSize);

}
