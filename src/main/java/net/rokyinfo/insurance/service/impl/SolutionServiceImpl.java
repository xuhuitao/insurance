package net.rokyinfo.insurance.service.impl;

import net.rokyinfo.insurance.dao.SolutionDao;
import net.rokyinfo.insurance.entity.SolutionEntity;
import net.rokyinfo.insurance.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("insSolutionService")
public class SolutionServiceImpl implements SolutionService {
    @Autowired
    private SolutionDao solutionDao;

    @Override
    public SolutionEntity queryObject(Long id) {
        return solutionDao.queryObject(id);
    }

    @Override
    public List<SolutionEntity> queryList(Map<String, Object> map) {
        return solutionDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return solutionDao.queryTotal(map);
    }

    @Override
    public void save(SolutionEntity insSolution) {
        solutionDao.save(insSolution);
    }

    @Override
    public void update(SolutionEntity insSolution) {
        solutionDao.update(insSolution);
    }

    @Override
    public void delete(Long id) {
        solutionDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        solutionDao.deleteBatch(ids);
    }

}
