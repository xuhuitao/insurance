package net.rokyinfo.insurance.service;

import net.rokyinfo.insurance.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 * 
 * @author zhijian.yuan
 * @email zhijian.yuan@gmail.com
 * @date 2018-01-05 13:18:54
 */
public interface UserService {
	
	UserEntity queryObject(Long id);
	
	List<UserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserEntity user);
	
	void update(UserEntity user);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
