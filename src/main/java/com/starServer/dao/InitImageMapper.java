package com.starServer.dao;

import com.starServer.entity.InitImage;
import com.starServer.entity.InitImageExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InitImageMapper {
    int countByExample(InitImageExample example);

    int deleteByExample(InitImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InitImage record);

    int insertSelective(InitImage record);

    List<InitImage> selectByExample(InitImageExample example);

    InitImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InitImage record, @Param("example") InitImageExample example);

    int updateByExample(@Param("record") InitImage record, @Param("example") InitImageExample example);

    int updateByPrimaryKeySelective(InitImage record);

    int updateByPrimaryKey(InitImage record);
}