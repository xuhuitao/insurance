package net.rokyinfo.insurance.service;

import net.rokyinfo.insurance.entity.SysLogEntity;
import java.util.List;
import java.util.Map;
/**
 * 系统操作日志
 * 
 * @author yangyang.cao
 * @email yangyang.cao@rokyinfo
 * @date 2018-01-22 15:40:12
 */
public interface SysLogService {
	
	SysLogEntity queryObject(Long id);
	
	List<SysLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysLogEntity sysLog);
	
	void update(SysLogEntity sysLog);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
