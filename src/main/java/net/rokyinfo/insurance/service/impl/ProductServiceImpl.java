package net.rokyinfo.insurance.service.impl;

import net.rokyinfo.insurance.dao.ProductDao;
import net.rokyinfo.insurance.entity.ProductEntity;
import net.rokyinfo.insurance.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("insProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public ProductEntity queryObject(Long id) {
        return productDao.queryObject(id);
    }

    @Override
    public List<ProductEntity> queryList(Map<String, Object> map) {
        return productDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return productDao.queryTotal(map);
    }

    @Override
    public void save(ProductEntity insProduct) {
        productDao.save(insProduct);
    }

    @Override
    public void update(ProductEntity insProduct) {
        productDao.update(insProduct);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        productDao.deleteBatch(ids);
    }

}
