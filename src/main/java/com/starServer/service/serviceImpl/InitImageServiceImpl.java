package com.starServer.service.serviceImpl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.starServer.dao.InitImageMapper;
import com.starServer.entity.InitImage;
import com.starServer.entity.InitImageExample;
import com.starServer.entity.response.ResponseData;
import com.starServer.service.InitImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zj
 */
@Service
public class InitImageServiceImpl implements InitImageService {
    @Autowired
    private InitImageMapper initImageMapper;


    @Override
    public int addInitImage(InitImage initImage) {
        return initImageMapper.insert(initImage);
    }

    @Override
    public int deleteInitImageById(Integer id) {
        return initImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateInitImage(InitImage initImage) {
        return initImageMapper.updateByPrimaryKey(initImage);
    }

    @Override
    public InitImage getInitImageById(Integer id) {
        return initImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResponseData<List<InitImage>> getAllInitImageByPage(int page, int pageSize) {
        ResponseData<List<InitImage>> responseData=new ResponseData<>();
        InitImageExample starExample=new InitImageExample();
        PageHelper.startPage(page,pageSize);
        List<InitImage> images=initImageMapper.selectByExample(starExample);
        PageInfo<InitImage> pageInfo=new PageInfo<>(images);
        responseData.setCount((int) pageInfo.getTotal());
        responseData.jsonFill(1,null,images);
        return responseData;
    }
}
