package com.starServer.service;

import com.starServer.entity.Star;
import com.starServer.entity.response.BooleanResponse;
import com.starServer.entity.response.ResponseData;

import java.util.List;

/**
 * Created by zhangsong on 2018/6/27.
 */
public interface StarService {
    ResponseData<List<Star>> getStarListByPage(int page, int pageSize);
    int saveStar(Star star);
    Star getStarById(Integer id);
    int updateStar(Star star);
    int deleteStar(Integer id);
}
