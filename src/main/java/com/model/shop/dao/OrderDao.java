package com.model.shop.dao;

import com.model.shop.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    public List<Order> getAllOrder() throws Exception;

    public List<Order> getOrderByUserId(Integer userId) throws Exception;

    public void deleteById(Integer orderId) throws Exception;

    public void save(Order order) throws Exception;

    public void update(Order order, Integer orderId) throws Exception;

    public Order getOrderById(Integer orderId) throws Exception;
}
