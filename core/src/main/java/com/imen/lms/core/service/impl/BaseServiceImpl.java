package com.imen.lms.core.service.impl;

import com.imen.lms.core.mapper.BaseMapper;
import com.imen.lms.core.page.BaseQuery;
import com.imen.lms.core.page.PageResult;
import com.imen.lms.core.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @autor LIGANG
 * @data 2018/10/13 13:51
 * @description
 */
@Service
@Transactional
public class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;
    @Autowired
    private BaseQuery bq;


    @Override
    public int deleteOne(Integer id) {
        return baseMapper.deleteOne(id);
    }

    @Override
    public List<T> listAll() {
        return baseMapper.listAll();
    }

    @Override
    public int updateOne(T obj) {
        return baseMapper.updateOne(obj);
    }

    @Override
    public int insertOne(T obj) {
        return baseMapper.insertOne(obj);
    }

    @Override
    public T getByID(Integer id) {
        return baseMapper.getByID(id);
    }

    /**
     * 分页+高级查询
     *
     * @param queryObj    查询条件
     * @param currentPage 当前页
     * @param pageSize    每页条数
     * @return
     */
    @Override
    public PageResult query(T queryObj, Integer currentPage, Integer pageSize) {
        if (currentPage == null) {
            currentPage = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Integer count = baseMapper.queryCount(queryObj);
        if (count <= 0) {
            return new PageResult(0, Collections.EMPTY_LIST, 1, pageSize);
        }
        List<T> listResult = baseMapper.query(queryObj);
        return new PageResult(count, listResult, currentPage, pageSize);
    }

}
