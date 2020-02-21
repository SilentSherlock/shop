package com.model.shop.dao;

import com.model.shop.entity.LoginUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginUserDao {

    public List<LoginUser> getAllUser() throws Exception;

    public List<LoginUser> getUserByName(String userName) throws Exception;

    public List<LoginUser> getUserByEmail(String email) throws Exception;

    public LoginUser getUserByNameAndPass(String userName, String password) throws Exception;

    public LoginUser getUserByEmailAndPass(String email, String password) throws Exception;

    public void deleteById(Integer userId) throws Exception;

    public void save(LoginUser loginUser) throws Exception;

    public void update(LoginUser loginUser, Integer userId) throws Exception;

}
