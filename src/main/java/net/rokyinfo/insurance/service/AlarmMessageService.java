package net.rokyinfo.insurance.service;

import net.rokyinfo.insurance.entity.AlarmMessageEntity;

import java.util.List;
import java.util.Map;

/**
 * 告警消息表
 * 
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-10 23:00:14
 */
public interface AlarmMessageService {
	
	AlarmMessageEntity queryObject(Long id);
	
	List<AlarmMessageEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AlarmMessageEntity alarmMessage);
	
	void update(AlarmMessageEntity alarmMessage);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
