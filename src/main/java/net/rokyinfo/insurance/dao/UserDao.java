package net.rokyinfo.insurance.dao;

import net.rokyinfo.insurance.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表
 *
 * @author zhijian.yuan
 * @email zhijian.yuan@gmail.com
 * @date 2018-01-05 13:18:54
 */
@Mapper
public interface UserDao extends BaseDao<UserEntity> {

    /**
     * get user by username
     *
     * @param username
     * @return
     */
    UserEntity queryUserByUserName(@Param("username") String username);

}
