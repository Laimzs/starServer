package com.starServer.service.serviceImpl;

import com.starServer.dao.UserMapper;
import com.starServer.entity.User;
import com.starServer.entity.UserExample;
import com.starServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by zhangsong on 2018/1/1.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    public User getUserByPhone(String phone) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<User> userList=userMapper.selectByExample(userExample);
        if (userList!=null&&!userList.isEmpty()){
            return  userList.get(0);
        }
        return null;
    }

    public int saveUser(User user) {
        return userMapper.insert(user);
    }


    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }
}
