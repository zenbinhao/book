package com.isme.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.dao.RecordDao;
import com.isme.po.Record;
import com.isme.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao recordDao;

    public BaseModel selectRecord(String name, int curr_page, int page_size) throws Exception {
        BaseModel baseModel = new BaseModel();
        PageHelper.startPage(curr_page, page_size);
        List<Record> list =  recordDao.selectRecord(name);
        PageInfo pageInfo = new PageInfo(list,5);
        baseModel.setMessage("分页查询借还书记录信息成功");
        baseModel.setCode(0);
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel selectRecordById(int id, int curr_page, int page_size) throws Exception {
        BaseModel baseModel = new BaseModel();
        PageHelper.startPage(curr_page, page_size);
        List<Record> list =  recordDao.selectRecordById(id);
        PageInfo pageInfo = new PageInfo(list,5);
        baseModel.setMessage("分页查询借还书记录信息成功");
        baseModel.setCode(0);
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel updateReturnRecord(Record record) throws Exception {
        int row = recordDao.updateReturnRecord(record);
        BaseModel baseModel=new BaseModel();
        if(row<=0){
            throw new BusinessException(1040,"还书操作超时");
        }
        baseModel.setMessage("还书成功");
        baseModel.setCode(0);
        return baseModel;
    }

    public BaseModel insertRecord(Record record) throws Exception {
        BaseModel baseModel = new BaseModel();
        System.out.println("***************\n" +
                record.getUser_id() +
                "***************\n" +
                "***************\n");
        int rowState = recordDao.selectRecordBookState(record.getUser_id());
        if(rowState > 0) {
            throw new BusinessException(5905,"您有书籍还未归还");
        }
        int row= recordDao.insertRecord(record);
        if (row<=0){
            throw new BusinessException(1041,"借书操作超时");
        }
        baseModel.setData(row);
        baseModel.setMessage("成功借书,请及时到图书馆领取,如若误点请联系图书管理员");
        baseModel.setCode(0);
        return baseModel;
    }

    public BaseModel deleteRecord(String ids)throws Exception {
        BaseModel baseModel = new BaseModel();
        String []id =ids.split(",");

        int row = recordDao.deleteRecord(id);
        if (row <=0||ids.length()<=0) {
            throw new BusinessException(1042,"借还书信息删除操作失败");
        }
        baseModel.setMessage("删除"+row+"条数据成功");
        baseModel.setCode(0);
        return baseModel;
    }

    public BaseModel selectRecordBookState(int id) throws Exception {
        BaseModel baseModel = new BaseModel();
        int row = recordDao.selectRecordBookState(id);
        int reData= 0;
        if (row>0){
            reData = 1;
        }
        baseModel.setData(reData);
        baseModel.setMessage("状态已找到");
        baseModel.setCode(0);
        return baseModel;
    }

}
