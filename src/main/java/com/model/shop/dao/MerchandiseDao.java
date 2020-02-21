package com.model.shop.dao;

import com.model.shop.entity.Merchandise;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchandiseDao {
    public List<Merchandise> getAllMer() throws Exception;

    public Merchandise getMerByName(String merName) throws Exception;

    public Merchandise getMerById(Integer merId) throws Exception;

    public void deleteById(Integer merId) throws Exception;

    public void save(Merchandise merchandise) throws Exception;

    public void update(Merchandise merchandise, Integer merId) throws Exception;
}
