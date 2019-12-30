package com.imooc.service.impl;

import com.github.pagehelper.PageInfo;
import com.imooc.service.BaseService;
import com.imooc.utils.PagedGridResult;

import java.util.List;

public class BaseServiceImpl implements BaseService {

    @Override
    public PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
