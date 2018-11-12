package com.baizhi.service;

import com.baizhi.entity.Banner;

public interface BannerService extends BaseService<Banner> {
    Integer findCount();
    void removeAll(String[] ids);
}
