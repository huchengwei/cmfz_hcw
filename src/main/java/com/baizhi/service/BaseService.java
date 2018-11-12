package com.baizhi.service;

import java.util.List;

public interface BaseService<T> {
    void add(T t);
    List<T> findAll();
    void remove(String id);
    void motify(T t);
    T findOne(T t);
    List<T> findByPage(Integer page,Integer rows);
}
