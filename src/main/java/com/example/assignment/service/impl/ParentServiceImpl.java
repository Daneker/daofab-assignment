package com.example.assignment.service.impl;

import com.example.assignment.dao.ParentDao;
import com.example.assignment.model.Parent;
import com.example.assignment.model.pojo.Page;
import com.example.assignment.model.pojo.Pagination;
import com.example.assignment.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {
    private final ParentDao parentDao;

    @Override
    public Page<Parent> getList(Pagination pagination, String orderBy) {
        List<Parent> list = parentDao.getList(pagination, orderBy);
        Long contentListTotal = parentDao.countTotal();
        return new Page<>(list, pagination, contentListTotal);
    }
}
