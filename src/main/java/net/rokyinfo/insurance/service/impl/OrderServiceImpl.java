package net.rokyinfo.insurance.service.impl;

import net.rokyinfo.insurance.dao.OrderDao;
import net.rokyinfo.insurance.entity.OrderEntity;
import net.rokyinfo.insurance.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("insOrderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public OrderEntity queryObject(Long id) {
        return orderDao.queryObject(id);
    }

    @Override
    public List<OrderEntity> queryList(Map<String, Object> map) {
        return orderDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderDao.queryTotal(map);
    }

    @Override
    public void save(OrderEntity insOrder) {
        orderDao.save(insOrder);
    }

    @Override
    public void update(OrderEntity insOrder) {
        orderDao.update(insOrder);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        orderDao.deleteBatch(ids);
    }

}
