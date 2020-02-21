package com.model.shop.service;

import com.model.shop.dao.AdminDao;
import com.model.shop.entity.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminDao adminDao;

    public AdminDao getAdminDao() {
        return adminDao;
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    //管理员登录验证
    public boolean adminValidate(String adminName, String password) {
        try {
            List<Admin> adminList = adminDao.getAdminByName(adminName);
            for (Admin ad : adminList) {
                if (password != null && password.equals(ad.getPassword()))
                    return true;
                System.out.println(ad.getAdminName() + " " + ad.getPassword());
            }
        } catch (Exception e) {
            System.out.println("AdminService" + "adminValidate failed");
        }
        return false;
    }

    //获得所有管理员信息
    public List<Admin> getAllAdmins() throws Exception {
        return adminDao.getAllAdmins();
    }

    //获得管理员信息
    public Admin getAdminByNameAndPass(String adminName, String password) throws Exception {
        return adminDao.getAdminByNameAndPass(adminName, password);
    }

    //添加新的管理员
    public void addAdmin(String adminName, String password, String phone) throws Exception {
        Admin admin = new Admin();
        admin.setAdminName(adminName);
        admin.setPassword(password);
        admin.setPhone(phone);
        adminDao.save(admin);
    }

    //删除现有管理员
    public void deleteAdmin(String adminName, String password) throws Exception {
        Admin admin = adminDao.getAdminByNameAndPass(adminName, password);
        adminDao.deleteById(admin.getId());
    }

    //更改管理员信息
    public void updateAdmin(Admin admin) throws Exception {
        adminDao.update(admin, admin.getId());
    }
}
