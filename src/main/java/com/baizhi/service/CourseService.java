package com.baizhi.service;

import com.baizhi.entity.Course;

public interface CourseService extends BaseService<Course> {
    Integer findCount();
    void removeAll(String[] ids);
}
