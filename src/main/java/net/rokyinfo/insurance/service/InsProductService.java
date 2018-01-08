package net.rokyinfo.insurance.service;

import net.rokyinfo.insurance.entity.InsProductEntity;
import java.util.List;
import java.util.Map;
/**
 * 保险产品表
 * 
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
public interface InsProductService {
	
	InsProductEntity queryObject(Long id);
	
	List<InsProductEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(InsProductEntity insProduct);
	
	void update(InsProductEntity insProduct);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
