package com.isme.dao;

import com.isme.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int insertUser(User user);
    User selectByLogin(@Param("account") String account, @Param("password") String password);
    List<User> selectPageUser(@Param("userName") String user_name);
    int selectByAccount(@Param("at") String account);
    int selectByPhone(@Param("phone") String phone);
    int deleteUser(@Param("ids") String[] ids);
    int updateUser(User user);
    User selectById(@Param("id") int id);
}
