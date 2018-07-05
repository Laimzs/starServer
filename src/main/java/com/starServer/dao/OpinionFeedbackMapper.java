package com.starServer.dao;


import com.starServer.entity.OpinionFeedback;
import com.starServer.entity.OpinionFeedbackExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionFeedbackMapper {
    int countByExample(OpinionFeedbackExample example);

    int deleteByExample(OpinionFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpinionFeedback record);

    int insertSelective(OpinionFeedback record);

    List<OpinionFeedback> selectByExample(OpinionFeedbackExample example);

    OpinionFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpinionFeedback record, @Param("example") OpinionFeedbackExample example);

    int updateByExample(@Param("record") OpinionFeedback record, @Param("example") OpinionFeedbackExample example);

    int updateByPrimaryKeySelective(OpinionFeedback record);

    int updateByPrimaryKey(OpinionFeedback record);

    List<OpinionFeedback> getOpinionsWithUserName();
}