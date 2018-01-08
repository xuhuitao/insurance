package net.rokyinfo.insurance.service;

import net.rokyinfo.insurance.entity.ProductEntity;

import java.util.List;
import java.util.Map;

/**
 * 保险产品表
 *
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
public interface ProductService {

    ProductEntity queryObject(Long id);

    List<ProductEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(ProductEntity insProduct);

    void update(ProductEntity insProduct);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
