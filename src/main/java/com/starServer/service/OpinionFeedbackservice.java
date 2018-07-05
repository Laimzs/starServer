package com.starServer.service;


import com.starServer.entity.OpinionFeedback;
import com.starServer.entity.response.ResponseData;

import java.util.List;

/**
 * Created by zhangsong on 2017/11/20.
 */
public interface OpinionFeedbackservice {
    int saveOpinion(OpinionFeedback opinionFeedback);
    ResponseData<List<OpinionFeedback>> getOpinionsByPage(Integer page, Integer pageSize);
}
