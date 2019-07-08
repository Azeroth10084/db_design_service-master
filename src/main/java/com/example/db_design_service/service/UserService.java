package com.example.db_design_service.service;

import com.example.db_design_service.bean.User;
import com.example.db_design_service.bean.UserLogin;
import com.example.db_design_service.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserDao userDao;




    /**
     * 查找所有用户
     */
    public List<User> selectAllUser() {
        return userDao.findAllUser();
    }

    public List<UserLogin> selectAllUserLogin() {
        return userDao.findAllUserLogin();
    }
}
