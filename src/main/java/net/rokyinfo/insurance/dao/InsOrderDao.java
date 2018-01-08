package net.rokyinfo.insurance.dao;

import net.rokyinfo.insurance.entity.InsOrderEntity;
import org.apache.ibatis.annotations.Mapper;
/**
 * 保险订单表
 * 
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
@Mapper
public interface InsOrderDao extends BaseDao<InsOrderEntity> {
	
}
