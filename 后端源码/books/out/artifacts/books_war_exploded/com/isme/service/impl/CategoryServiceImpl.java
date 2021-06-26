package com.isme.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.dao.CategoryDao;
import com.isme.po.Category;
import com.isme.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    public BaseModel selectPageCategory(String categoryName, int curr_page, int page_size) throws Exception {
        BaseModel baseModel = new BaseModel();
        PageHelper.startPage(curr_page, page_size);
        List<Category> list =  categoryDao.selectPageCategory(categoryName);
        PageInfo pageInfo = new PageInfo(list,5);
        baseModel.setMessage("查询所有书籍的类别信息成功");
        baseModel.setCode(0);
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel insertCategory(Category category) throws Exception {
        BaseModel baseModel = new BaseModel();
        int row = categoryDao.insertCategory(category);
        if (row<=0){
            throw new BusinessException(1021,"图书的类别信息添加失败");
        }
        baseModel.setCode(0);
        baseModel.setData(row);
        baseModel.setMessage("图书的类别信息信息添加成功");
        return baseModel;
    }

    public BaseModel deleteCategory(String ids) throws Exception {
        BaseModel baseModel = new BaseModel();
        String []id =ids.split(",");

        int row = categoryDao.deleteCategory(id);
        if (row <=0||ids.length()<=0) {
            throw new BusinessException(1022,"图书的类别信息删除操作失败");
        }
        baseModel.setMessage("删除"+row+"条数据成功");
        baseModel.setCode(0);
        return baseModel;
    }

    public BaseModel updateCategory(Category category) throws Exception {
        int row = categoryDao.updateCategory(category);
        BaseModel baseModel=new BaseModel();
        if(row<=0){
            throw new BusinessException(1123,"修改图书的类别信息操作失败");
        }
        baseModel.setMessage("修改图书的类别信息成功");
        baseModel.setCode(0);
        return baseModel;
    }

    public BaseModel selectCategoryById(int id) throws Exception {
        BaseModel baseModel = new BaseModel();
        Category category = categoryDao.selectCategoryById(id);
        if (category==null){
            throw new BusinessException(2005,"查询id:"+id+"的类别不存在");
        }
        baseModel.setData(category);
        baseModel.setCode(0);
        baseModel.setMessage("查询id为:"+id+"的类别成功");
        return baseModel;
    }
}
