package com.baizhi.dao;

import java.util.List;

public interface BaseDao<T> {
    void insert(T t);
    List<T> queryAll();
    T queryOne(T t);
    void delete(String id);
    void update(T t);
    List<T> queryByPage(Integer start,Integer rows);
}
