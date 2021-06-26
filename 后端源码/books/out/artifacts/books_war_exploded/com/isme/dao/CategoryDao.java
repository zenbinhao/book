package com.isme.dao;

import com.isme.po.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {
    List<Category> selectPageCategory(@Param("name") String categoryName);
    int insertCategory(Category category);
    int deleteCategory(@Param("ids") String[] ids);
    int updateCategory(Category category);
    Category selectCategoryById(@Param("id") int id);
}
