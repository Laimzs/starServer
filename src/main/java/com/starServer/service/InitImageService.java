package com.starServer.service;

import com.starServer.entity.InitImage;
import com.starServer.entity.response.ResponseData;

import java.util.List;

/**
 * @author zj
 */
public interface InitImageService {
    int addInitImage(InitImage initImage);
    int deleteInitImageById(Integer id);
    int  updateInitImage(InitImage initImage);
    InitImage getInitImageById(Integer id);
    ResponseData<List<InitImage>> getAllInitImageByPage(int page, int pageSize);
}
