package com.starServer.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.starServer.dao.StarMapper;
import com.starServer.entity.Star;
import com.starServer.entity.StarExample;
import com.starServer.entity.response.BooleanResponse;
import com.starServer.entity.response.ResponseData;
import com.starServer.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangsong on 2018/6/27.
 */

@Service
public class StarServiceImpl implements StarService {
    @Autowired
    private StarMapper starMapper;
    @Override
    public ResponseData<List<Star>> getStarListByPage(int page, int pageSize) {
        ResponseData<List<Star>> responseData=new ResponseData<>();
        StarExample starExample=new StarExample();
        PageHelper.startPage(page,pageSize);
        List<Star> starList=starMapper.selectByExample(starExample);
        PageInfo<Star> pageInfo=new PageInfo<>(starList);
        responseData.setCount((int) pageInfo.getTotal());
        responseData.jsonFill(1,null,starList);
        return responseData;
    }

    @Override
    public int saveStar(Star star) {
        return starMapper.insert(star);
    }

    @Override
    public Star getStarById(Integer id) {
        return starMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStar(Star star) {
        return starMapper.updateByPrimaryKey(star);
    }

    @Override
    public int deleteStar(Integer id) {
        return starMapper.deleteByPrimaryKey(id);
    }
}
