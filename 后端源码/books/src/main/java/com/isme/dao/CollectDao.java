package com.isme.dao;

import com.isme.dto.SelectCollect;
import com.isme.po.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectDao {
    List<SelectCollect> selectCollectById(@Param("user_id") int user_id);
    int insertCollect(Collect collect);
    // 该书籍是否收藏
    int selectIs(@Param("user_id") int user_id,@Param("book_id") int book_id);
    // 取消收藏 真删除
    int deleteCollect(@Param("user_id") int user_id,@Param("book_id") int book_id);
}
