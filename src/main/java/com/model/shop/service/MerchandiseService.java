package com.model.shop.service;

import com.model.shop.dao.MerchandiseDao;
import com.model.shop.entity.Merchandise;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MerchandiseService {

    @Resource
    private MerchandiseDao merchandiseDao;

    public MerchandiseDao getMerchandiseDao() {
        return merchandiseDao;
    }

    public void setMerchandiseDao(MerchandiseDao merchandiseDao) {
        this.merchandiseDao = merchandiseDao;
    }

    public List<Merchandise> getAllMer() throws Exception {
        return merchandiseDao.getAllMer();
    }

    //精确查询
    public Merchandise searchMerByName(String name) throws Exception {
        return merchandiseDao.getMerByName(name);
    }

    //根据商品ID查询商品
    public Merchandise getMerById(Integer merId) throws Exception {
        return merchandiseDao.getMerById(merId);
    }

    //根据商品ID删除某项商品
    public void deleteMerById(Integer Id) throws Exception {
        merchandiseDao.deleteById(Id);
    }

    public void addMerchandise(Merchandise merchandise) throws Exception {
        merchandiseDao.save(merchandise);
    }

    public void updateMerchandise(Merchandise merchandise, Integer Id) throws Exception {
        merchandiseDao.update(merchandise, Id);
    }
}
