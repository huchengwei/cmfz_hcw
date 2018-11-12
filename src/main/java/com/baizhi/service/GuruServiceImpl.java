package com.baizhi.service;

import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Guru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {

    @Autowired
    private GuruDao guruDao;

    @Override
    public void add(Guru guru) {
        guru.setId(UUID.randomUUID().toString());
        guruDao.insert(guru);
    }

    @Override
    public List<Guru> findAll() {
        return null;
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public void motify(Guru guru) {

    }

    @Override
    public Guru findOne(Guru guru) {
        return null;
    }

    @Override
    public List<Guru> findByPage(Integer page, Integer rows) {
        int start=(page-1)*rows;
        return guruDao.queryByPage(start,rows);
    }

    @Override
    public Integer findCount() {
        return guruDao.queryCount();
    }

    @Override
    public void removeAll(String[] ids) {
        for (String id:ids){
            guruDao.delete(id);
        }
    }
}
