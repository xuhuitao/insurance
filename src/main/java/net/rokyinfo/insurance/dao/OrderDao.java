package net.rokyinfo.insurance.dao;

import net.rokyinfo.insurance.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
/**
 * 保险订单表
 *
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
@Mapper
public interface OrderDao extends BaseDao<OrderEntity> {

    List<String> queryCcuSnOfOrder(Map<String, Object> map);

    OrderEntity queryOrderByOrderNo(@Param("orderNo")String orderNo);

    List<OrderEntity> queryListByStatusArray(Map<String, Object> map);

    int queryTotalByStatusArray(Map<String, Object> map);

}
