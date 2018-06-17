package com.starServer.service.serviceImpl;

import com.starServer.dao.FireBehaviorInformationMapper;
import com.starServer.entity.FireBehaviorInformation;
import com.starServer.entity.FireBehaviorInformationExample;
import com.starServer.service.FireInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangsong on 2018/1/4.
 */
@Service
public class FireInformationServiceImpl implements FireInformationService {
    @Autowired
    FireBehaviorInformationMapper fireBehaviorInformationMapper;
    @Override
    public List<FireBehaviorInformation> getFireInformation(int level) {
        FireBehaviorInformationExample fireBehaviorInformationExample=new FireBehaviorInformationExample();
        FireBehaviorInformationExample.Criteria criteria=fireBehaviorInformationExample.createCriteria();
        criteria.andLevelEqualTo(level);
        List<FireBehaviorInformation> list=fireBehaviorInformationMapper.selectByExample(fireBehaviorInformationExample);
        return list;
    }

    @Override
    public FireBehaviorInformation getFireInformationById(int id) {
        return fireBehaviorInformationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateFireInformation(FireBehaviorInformation fireBehaviorInformation) {
        return fireBehaviorInformationMapper.updateByPrimaryKey(fireBehaviorInformation);
    }

    @Override
    public List<FireBehaviorInformation> getFireInformationHistory(int level) {
        FireBehaviorInformationExample fireBehaviorInformationExample=new FireBehaviorInformationExample();
        FireBehaviorInformationExample.Criteria criteria=fireBehaviorInformationExample.createCriteria();
        criteria.andLevelNotBetween(0,level);
        List<FireBehaviorInformation> list=fireBehaviorInformationMapper.selectByExample(fireBehaviorInformationExample);
        return list;
    }
}
