package net.rokyinfo.insurance.dao;

import net.rokyinfo.insurance.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 保险产品表
 *
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
@Mapper
public interface ProductDao extends BaseDao<ProductEntity> {

}
