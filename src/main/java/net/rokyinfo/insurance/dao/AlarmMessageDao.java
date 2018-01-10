package net.rokyinfo.insurance.dao;

import net.rokyinfo.insurance.entity.AlarmMessageEntity;
import org.apache.ibatis.annotations.Mapper;
/**
 * 告警消息表
 * 
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-10 23:00:14
 */
@Mapper
public interface AlarmMessageDao extends BaseDao<AlarmMessageEntity> {
	
}
