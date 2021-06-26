package com.isme.dao;

import com.isme.po.Examine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamineDao {
    List<Examine> selectPageExamine(@Param("purchase_book") String purchase_book);
    int insertExamine(Examine examine);
    int deleteExamine(@Param("ids") String[] ids);
    int updateExamine(Examine examine);
    Examine selectExamineById(@Param("id") int id);
}
