package com.starServer.dao;

import com.starServer.entity.FireBehaviorInformation;
import com.starServer.entity.FireBehaviorInformationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireBehaviorInformationMapper {
    int countByExample(FireBehaviorInformationExample example);

    int deleteByExample(FireBehaviorInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FireBehaviorInformation record);

    int insertSelective(FireBehaviorInformation record);

    List<FireBehaviorInformation> selectByExample(FireBehaviorInformationExample example);

    FireBehaviorInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FireBehaviorInformation record, @Param("example") FireBehaviorInformationExample example);

    int updateByExample(@Param("record") FireBehaviorInformation record, @Param("example") FireBehaviorInformationExample example);

    int updateByPrimaryKeySelective(FireBehaviorInformation record);

    int updateByPrimaryKey(FireBehaviorInformation record);
}