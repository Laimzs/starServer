package com.starServer.service;

import com.starServer.entity.FireBehaviorInformation;

import java.util.List;

/**
 * Created by zhangsong on 2018/1/4.
 */
public interface FireInformationService {
    List<FireBehaviorInformation> getFireInformation(int level);
    FireBehaviorInformation getFireInformationById(int id);
    int updateFireInformation(FireBehaviorInformation fireBehaviorInformation);
    List<FireBehaviorInformation> getFireInformationHistory(int level);
}
