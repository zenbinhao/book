package com.isme.service;

import com.isme.base.vo.BaseModel;
import com.isme.po.Category;


public interface CategoryService {
    BaseModel selectPageCategory(String categoryName, int curr_page, int page_size)throws Exception;
    BaseModel insertCategory(Category category)throws Exception;
    BaseModel deleteCategory(String ids)throws Exception;
    BaseModel updateCategory(Category category)throws Exception;
    BaseModel selectCategoryById(int id)throws Exception;
}
