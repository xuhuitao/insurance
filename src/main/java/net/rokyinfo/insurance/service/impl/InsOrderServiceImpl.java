package net.rokyinfo.insurance.service.impl;

import net.rokyinfo.insurance.dao.InsOrderDao;
import net.rokyinfo.insurance.entity.InsOrderEntity;
import net.rokyinfo.insurance.service.InsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service("insOrderService")
public class InsOrderServiceImpl implements InsOrderService {

	@Autowired
	private InsOrderDao insOrderDao;
	
	@Override
	public InsOrderEntity queryObject(Long id){
		return insOrderDao.queryObject(id);
	}
	
	@Override
	public List<InsOrderEntity> queryList(Map<String, Object> map){
		return insOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return insOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(InsOrderEntity insOrder){
		insOrderDao.save(insOrder);
	}
	
	@Override
	public void update(InsOrderEntity insOrder){
		insOrderDao.update(insOrder);
	}
	
	@Override
	public void delete(Long id){
		insOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		insOrderDao.deleteBatch(ids);
	}
	
}
