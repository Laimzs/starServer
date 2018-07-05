package com.starServer.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.starServer.dao.UserMapper;
import com.starServer.entity.User;
import com.starServer.entity.UserExample;
import com.starServer.entity.response.BooleanResponse;
import com.starServer.entity.response.ResponseData;
import com.starServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import javax.jws.soap.SOAPBinding;
import java.util.Date;
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
        criteria.andMobileEqualTo(phone);
        List<User> userList=userMapper.selectByExample(userExample);
        if (userList!=null&&!userList.isEmpty()){
            return  userList.get(0);
        }
        return null;
    }

    @Override
    public ResponseData<List<User>> getUserListByPage(int page, int pageSize) {
        ResponseData<List<User>> responseData=new ResponseData<>();
        PageHelper.startPage(page,pageSize);
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andValidEqualTo(1);
        List<User> userList=userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo=new PageInfo<>(userList);
        responseData.setCount((int) pageInfo.getTotal());
        responseData.jsonFill(1,null,userList);
        return responseData;
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

    @Override
    public BooleanResponse deleteUser(Integer id) {
        BooleanResponse booleanResponse=BooleanResponse.build();
        User user = userMapper.selectByPrimaryKey(id);
        if (user==null){
            return booleanResponse.fail("用户不存在");
        }
        user.setValid(0);
        user.setUpdateTime(new Date());
        int i = userMapper.updateByPrimaryKey(user);
        if (i!=1){
            return booleanResponse.fail("删除失败");
        }
        return booleanResponse;
    }

    @Override
    public ResponseData<List<User>> getAllCancelUsers(int page, int pageSize) {
        ResponseData<List<User>> responseData=new ResponseData<>();
        PageHelper.startPage(page,pageSize);
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andValidEqualTo(2);
        List<User> userList=userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo=new PageInfo<>(userList);
        responseData.setCount((int) pageInfo.getTotal());
        responseData.jsonFill(1,null,userList);
        return responseData;
    }
}
