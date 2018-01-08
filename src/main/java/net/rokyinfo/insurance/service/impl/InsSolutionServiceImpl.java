package net.rokyinfo.insurance.service.impl;

import net.rokyinfo.insurance.dao.InsSolutionDao;
import net.rokyinfo.insurance.entity.InsSolutionEntity;
import net.rokyinfo.insurance.service.InsSolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service("insSolutionService")
public class InsSolutionServiceImpl implements InsSolutionService {
	@Autowired
	private InsSolutionDao insSolutionDao;
	
	@Override
	public InsSolutionEntity queryObject(Long id){
		return insSolutionDao.queryObject(id);
	}
	
	@Override
	public List<InsSolutionEntity> queryList(Map<String, Object> map){
		return insSolutionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return insSolutionDao.queryTotal(map);
	}
	
	@Override
	public void save(InsSolutionEntity insSolution){
		insSolutionDao.save(insSolution);
	}
	
	@Override
	public void update(InsSolutionEntity insSolution){
		insSolutionDao.update(insSolution);
	}
	
	@Override
	public void delete(Long id){
		insSolutionDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		insSolutionDao.deleteBatch(ids);
	}
	
}
