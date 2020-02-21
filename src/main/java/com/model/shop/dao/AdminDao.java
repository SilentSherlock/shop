package com.model.shop.dao;

import com.model.shop.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

//采用映射文件的方式编写dao层
@Repository
public interface AdminDao {
    public List<Admin> getAdminByName(String adminName) throws Exception;

    public Admin getAdminByNameAndPass(String adminName, String password) throws Exception;

    public List<Admin> getAllAdmins() throws Exception;

    public void deleteById(Integer Id) throws Exception;

    public void save(Admin admin) throws Exception;

    public void update(Admin admin, Integer Id) throws Exception;
}
