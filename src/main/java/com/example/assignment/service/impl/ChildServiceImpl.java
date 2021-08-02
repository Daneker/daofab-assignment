package com.example.assignment.service.impl;

import com.example.assignment.dao.ChildDao;
import com.example.assignment.dao.ParentDao;
import com.example.assignment.model.Child;
import com.example.assignment.model.Parent;
import com.example.assignment.service.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {
    private final ChildDao childDao;
    private final ParentDao parentDao;

    @Override
    public List<Child> getList(Long parentId) {
        Parent parentTransaction = parentDao.findOne(parentId);
        List<Child> list = childDao.getList(parentId);

        // when getting list of children from ChildDao, we ge sender, receiver & totalAmount as nulls,
        // therefore we fill them here
        list.forEach(ch -> {
            ch.setSender(parentTransaction.getSender());
            ch.setReceiver(parentTransaction.getReceiver());
            ch.setTotalAmount(parentTransaction.getTotalAmount());
        });

        return list;
    }
}
