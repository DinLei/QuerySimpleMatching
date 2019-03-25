package com.zhaopin.algorithm.queryMatchingTask.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.zhaopin.algorithm.queryMatchingTask.repository.IJdRedisDao;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;

@Service
public class JdRedisDaoImpl implements IJdRedisDao {

    private RedisTemplate<String, String> redisTemplate;
    private HashOperations<String, String, String> hashOps;

    @Autowired
    public JdRedisDaoImpl(RedisTemplate<String, String> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOps = redisTemplate.opsForHash();
    }

    @Override
    public String getJD(String companyId, String jdId) {
        return hashOps.get(companyId, jdId);
    }

    @Override
    public int getNumOfJDs(String companyId) {
        return hashOps.size(companyId).intValue();
    }

    @Override
    public Map<String, String> getAllJDs(String companyId) {
        return hashOps.entries(companyId);
    }

    @Override
    public Set<String> getAllJDIds(String companyId) {
        return hashOps.keys(companyId);
    }
}
