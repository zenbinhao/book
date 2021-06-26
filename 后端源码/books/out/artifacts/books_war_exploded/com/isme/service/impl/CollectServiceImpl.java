package com.isme.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.dao.CollectDao;
import com.isme.dto.SelectCollect;
import com.isme.po.Collect;
import com.isme.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    CollectDao collectDao;
    public BaseModel selectCollectById(int user_id,int curr_page, int page_size) throws Exception {
        BaseModel baseModel =new BaseModel();
        PageHelper.startPage(curr_page,page_size);
        List<SelectCollect> list = collectDao.selectCollectById(user_id);
        PageInfo pageInfo = new PageInfo(list);
        baseModel.setCode(0);
        baseModel.setMessage("分页查询收藏书籍成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel insertCollect(Collect collect) throws Exception {
        BaseModel baseModel = new BaseModel();
        int row = collectDao.insertCollect(collect);
        if (row<=0) {
            throw new BusinessException(5423,"收藏失败");
        }
        baseModel.setCode(0);
        baseModel.setMessage("收藏成功");
        baseModel.setData(row);
        return baseModel;
    }

    public BaseModel selectIs(int user_id, int book_id) throws Exception {
        BaseModel baseModel= new BaseModel();
        int row = collectDao.selectIs(user_id, book_id);
        if (row>0){
            baseModel.setData(1);
            baseModel.setMessage("已收藏");
            baseModel.setCode(0);
        }else {
            baseModel.setData(0);
            baseModel.setMessage("未收藏");
            baseModel.setCode(0);
        }
        return baseModel;
    }

    public BaseModel deleteCollect(int user_id, int book_id) throws Exception {
        BaseModel baseModel = new BaseModel();
        int row= collectDao.deleteCollect(user_id, book_id);
        if(row<=0){
            throw new BusinessException(4654,"取消收藏失败");
        }
        baseModel.setCode(0);
        baseModel.setData(row);
        baseModel.setMessage("成功的取消了收藏");
        return baseModel;
    }
}
