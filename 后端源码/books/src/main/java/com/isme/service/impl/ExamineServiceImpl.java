package com.isme.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.dao.ExamineDao;
import com.isme.po.Examine;
import com.isme.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExamineServiceImpl implements ExamineService {

    @Autowired
    ExamineDao examineDao;

    public BaseModel selectPageExamine(String purchase_book, int curr_page, int page_size) throws Exception {
        BaseModel baseModel = new BaseModel();
        PageHelper.startPage(curr_page, page_size);
        List<Examine> list = examineDao.selectPageExamine(purchase_book);
        PageInfo pageInfo = new PageInfo(list,5);
        baseModel.setMessage("查询进购图书审核信息成功");
        baseModel.setCode(0);
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel insertExamine(Examine examine) throws Exception {
        BaseModel baseModel = new BaseModel();
        int row = examineDao.insertExamine(examine);
        if (row<=0){
            throw new BusinessException(1051,"进购图书审核信息添加失败");
        }
        baseModel.setCode(0);
        baseModel.setData(row);
        baseModel.setMessage("进购图书审核信息添加成功！！");
        return baseModel;
    }

    public BaseModel deleteExamine(String ids) throws Exception {
        BaseModel baseModel = new BaseModel();
        String []id =ids.split(",");

        int row = examineDao.deleteExamine(id);
        if (row <=0||ids.length()<=0) {
            throw new BusinessException(1052,"进购图书审核信息删除操作失败");
        }
        baseModel.setMessage("删除"+row+"条数据成功");
        baseModel.setCode(0);
        return baseModel;
    }

    public BaseModel updateExamine(Examine examine) throws Exception {
        int row = examineDao.updateExamine(examine);
        BaseModel baseModel=new BaseModel();
        if(row<=0){
            throw new BusinessException(1053,"修改进购图书审核信息操作失败");
        }
        baseModel.setMessage("修改进购图书审核信息信息成功！！");
        baseModel.setCode(0);
        return baseModel;
    }

    public BaseModel selectExamineById(int id) throws Exception {
        BaseModel baseModel = new BaseModel();
        Examine examine = examineDao.selectExamineById(id);
        if (examine==null){
            throw new BusinessException(1054,"查询id:"+id+"的审核信息不存在");
        }
        baseModel.setData(examine);
        baseModel.setCode(0);
        baseModel.setMessage("查询id为:"+id+"的审核信息成功");
        return baseModel;
    }
}
