package com.model.shop.service;

import com.model.shop.dao.LoginUserDao;
import com.model.shop.entity.LoginUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginUserService {

    @Resource
    private LoginUserDao loginUserDao;

    public LoginUserDao getLoginUserDao() {
        return loginUserDao;
    }

    public void setLoginUserDao(LoginUserDao loginUserDao) {
        this.loginUserDao = loginUserDao;
    }

    public boolean userValidate(String name, String password) {
        try {
            List<LoginUser> userList = loginUserDao.getUserByName(name);
            for (LoginUser ad : userList) {
                if (password != null && password.equals(ad.getPassword()))
                    return true;
            }
        } catch (Exception e) {
            System.out.println("LoginUserService" + "userValidate failed");
        }
        return false;
    }

    public boolean userValidateByEmail(String email, String password) {
        try {
            List<LoginUser> userList = loginUserDao.getUserByEmail(email);
            for (LoginUser loginUser : userList) {
                if (password != null && password.equals(loginUser.getPassword())) {

                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("LoginUserService" + "userValidateByEmail failed");
        }
        return false;
    }

    public LoginUser getUserByEmailAndPass(String email, String password) throws Exception {
        return loginUserDao.getUserByEmailAndPass(email, password);
    }
}
