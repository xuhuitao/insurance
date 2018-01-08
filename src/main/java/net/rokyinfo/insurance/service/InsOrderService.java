package net.rokyinfo.insurance.service;

import net.rokyinfo.insurance.entity.InsOrderEntity;
import java.util.List;
import java.util.Map;
/**
 * 保险订单表
 * 
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
public interface InsOrderService {
	
	InsOrderEntity queryObject(Long id);
	
	List<InsOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(InsOrderEntity insOrder);
	
	void update(InsOrderEntity insOrder);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
