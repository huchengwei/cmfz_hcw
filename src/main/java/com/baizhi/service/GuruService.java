package com.baizhi.service;

import com.baizhi.entity.Guru;

public interface GuruService extends BaseService<Guru> {
    Integer findCount();
    void removeAll(String[] ids);
}
