package net.rokyinfo.insurance.service;

import net.rokyinfo.insurance.entity.OrderEntity;
import net.rokyinfo.insurance.util.R;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/**
 * 保险订单表
 *
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
public interface OrderService {

    OrderEntity queryObject(Long id);

    List<String> queryCcuSnOfOrder(Map<String, Object> map);

    OrderEntity queryOrderByOrderNo(String orderNo);

    List<OrderEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(OrderEntity insOrder);

    R save(OrderEntity insOrder, MultipartFile billFile, MultipartFile[] scooterFiles) throws IOException;

    R payInfo(String orderNo) throws IOException;

    void affirm(Long orderId, Integer dispose);

    void update(OrderEntity insOrder);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
