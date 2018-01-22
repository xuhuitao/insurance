package net.rokyinfo.insurance.dao;

import net.rokyinfo.insurance.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;
/**
 * 系统操作日志
 * 
 * @author yangyang.cao
 * @email yangyang.cao@rokyinfo
 * @date 2018-01-22 15:40:12
 */
@Mapper
public interface SysLogDao extends BaseDao<SysLogEntity> {
	
}
