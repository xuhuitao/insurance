package net.rokyinfo.insurance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import net.rokyinfo.insurance.dao.AlarmMessageDao;
import net.rokyinfo.insurance.entity.AlarmMessageEntity;
import net.rokyinfo.insurance.service.AlarmMessageService;



@Service("alarmMessageService")
public class AlarmMessageServiceImpl implements AlarmMessageService {
	@Autowired
	private AlarmMessageDao alarmMessageDao;
	
	@Override
	public AlarmMessageEntity queryObject(Long id){
		return alarmMessageDao.queryObject(id);
	}
	
	@Override
	public List<AlarmMessageEntity> queryList(Map<String, Object> map){
		return alarmMessageDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return alarmMessageDao.queryTotal(map);
	}
	
	@Override
	public void save(AlarmMessageEntity alarmMessage){
		alarmMessageDao.save(alarmMessage);
	}
	
	@Override
	public void update(AlarmMessageEntity alarmMessage){
		alarmMessageDao.update(alarmMessage);
	}
	
	@Override
	public void delete(Long id){
		alarmMessageDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		alarmMessageDao.deleteBatch(ids);
	}
	
}
