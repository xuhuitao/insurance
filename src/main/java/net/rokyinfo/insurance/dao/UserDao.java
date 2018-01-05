package net.rokyinfo.insurance.dao;

import net.rokyinfo.insurance.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author zhijian.yuan
 * @email zhijian.yuan@gmail.com
 * @date 2018-01-05 13:18:54
 */
@Mapper
public interface UserDao extends BaseDao<UserEntity> {
	
}
