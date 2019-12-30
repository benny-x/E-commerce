package com.imooc.service;

import com.imooc.utils.PagedGridResult;

import java.util.List;

public interface BaseService {


    /**
     * 分页
     * @param list
     * @param page
     * @return
     */
    PagedGridResult setterPagedGrid(List<?> list, Integer page);

}
