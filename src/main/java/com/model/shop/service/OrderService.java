package com.model.shop.service;

import com.model.shop.dao.OrderDao;
import com.model.shop.entity.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;

    public List<Order> getAllOrder() throws Exception {
        return orderDao.getAllOrder();
    }

    public List<Order> getOrderByUserId(Integer userId) throws Exception {
        return orderDao.getOrderByUserId(userId);
    }

    public void saveOrder(Order order) throws Exception {
        orderDao.save(order);
    }
}
