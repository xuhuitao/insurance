package net.rokyinfo.insurance.service.impl;

import net.rokyinfo.insurance.dao.InsProductDao;
import net.rokyinfo.insurance.entity.InsProductEntity;
import net.rokyinfo.insurance.service.InsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service("insProductService")
public class InsProductServiceImpl implements InsProductService {

	@Autowired
	private InsProductDao insProductDao;
	
	@Override
	public InsProductEntity queryObject(Long id){
		return insProductDao.queryObject(id);
	}
	
	@Override
	public List<InsProductEntity> queryList(Map<String, Object> map){
		return insProductDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return insProductDao.queryTotal(map);
	}
	
	@Override
	public void save(InsProductEntity insProduct){
		insProductDao.save(insProduct);
	}
	
	@Override
	public void update(InsProductEntity insProduct){
		insProductDao.update(insProduct);
	}
	
	@Override
	public void delete(Long id){
		insProductDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		insProductDao.deleteBatch(ids);
	}
	
}
