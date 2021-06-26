package com.isme.dao;

import com.isme.po.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordDao {
    List<Record> selectRecord(@Param("name") String name);
    List<Record> selectRecordById(@Param("id") int id);
    int updateReturnRecord(Record record);
    int insertRecord(Record record);
    int deleteRecord(@Param("ids") String []ids);
    int selectRecordBookState(@Param("id") int id);
}
