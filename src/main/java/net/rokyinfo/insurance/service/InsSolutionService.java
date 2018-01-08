package net.rokyinfo.insurance.service;

import net.rokyinfo.insurance.entity.InsSolutionEntity;
import java.util.List;
import java.util.Map;
/**
 * 保险产品方案表
 * 
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
public interface InsSolutionService {
	
	InsSolutionEntity queryObject(Long id);
	
	List<InsSolutionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(InsSolutionEntity insSolution);
	
	void update(InsSolutionEntity insSolution);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
