package net.rokyinfo.insurance.service.impl;

import net.rokyinfo.insurance.dao.UserDao;
import net.rokyinfo.insurance.entity.UserEntity;
import net.rokyinfo.insurance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity queryObject(Long id) {
        return userDao.queryObject(id);
    }

    @Override
    public List<UserEntity> queryList(Map<String, Object> map) {
        return userDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userDao.queryTotal(map);
    }

    @Override
    public void save(UserEntity user) {
        userDao.save(user);
    }

    @Override
    public void update(UserEntity user) {
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        userDao.deleteBatch(ids);
    }

    @Override
    public UserEntity queryUserByUserName(String username) {
        return userDao.queryUserByUserName(username);
    }

}
