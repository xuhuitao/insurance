package net.rokyinfo.insurance.service;

import net.rokyinfo.insurance.entity.Ebike;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface EbikeService {

    public List<Ebike> queryList(Map<String, Object> map) throws IOException;

    public int queryTotal(Map<String, Object> map);

}
