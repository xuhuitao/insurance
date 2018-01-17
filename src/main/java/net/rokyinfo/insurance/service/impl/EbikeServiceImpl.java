package net.rokyinfo.insurance.service.impl;

import net.rokyinfo.insurance.entity.Ebike;
import net.rokyinfo.insurance.entity.OrderEntity;
import net.rokyinfo.insurance.retrofit.RemoteService;
import net.rokyinfo.insurance.service.EbikeService;
import net.rokyinfo.insurance.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;

@Service("ebikeService")
public class EbikeServiceImpl implements EbikeService{

    @Autowired
    private RemoteService remoteService;

    @Autowired
    private OrderService orderService;

    @Override
    public List<Ebike> queryList(Map<String, Object> map) throws IOException {
        List<OrderEntity> orderEntityList = orderService.queryList(map);
        if (orderEntityList != null && orderEntityList.size() > 0) {
            Map<String, OrderEntity> orderEntityMap = new HashMap<>();
            orderEntityList.forEach(orderEntity -> {
                orderEntityMap.put(orderEntity.getCcuSn(), orderEntity);
            });
            List<String> ccuSnList = new ArrayList<>(orderEntityMap.keySet());
            List<Ebike> ebikeList = remoteService.getEbikeList(ccuSnList);
            if (ebikeList != null && ebikeList.size() > 0) {
                ebikeList.forEach(ebike -> {
                    OrderEntity orderEntity = orderEntityMap.get(ebike.getCcuSn());
                    ebike.setApplicant(orderEntity.getApplicant());
                });
            }
            return ebikeList;
        }
        return null;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderService.queryTotal(map);
    }
}
