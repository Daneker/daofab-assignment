package com.example.assignment.dao.impl;

import com.example.assignment.dao.ChildDao;
import com.example.assignment.dao.ParentDao;
import com.example.assignment.model.Child;
import com.example.assignment.model.Parent;
import com.example.assignment.model.dao.ParentData;
import com.example.assignment.model.pojo.Pagination;
import com.example.assignment.service.PreconditionService;
import com.example.assignment.util.StringUtils;
import com.example.assignment.util.constant.MessageCodeConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ParentDaoImpl implements ParentDao {

    private final ChildDao childDao;
    private final PreconditionService preconditionService;

    @Value("${settings.parent-path}")
    private String parentPath;

    private ParentData readParentData() {
        var objectMapper = new ObjectMapper();
        ParentData result = null;
        try {
            result = objectMapper.readValue(new File(parentPath), ParentData.class);
        } catch (Exception ex) {
            log.error("Exception: ", ex.getMessage());
        }
        return result;
    }

    @Override
    public List<Parent> getList(Pagination pagination, String orderBy) {
        var result = readParentData();
        preconditionService.checkNotNull(result, MessageCodeConstants.PARENT_DATA_NOT_FOUND);

        List<Parent> data = result.getData();
        String orderParam = "";
        String orderByParam = "";

        if(orderBy != null) {
            orderParam = StringUtils.parseOrderParameter(orderBy);
            orderByParam = StringUtils.parseOrderBy(orderBy);
        }

        if(orderParam.equals(StringUtils.ID) && orderByParam.equals(StringUtils.ASC)) {
            data = data.stream().sorted(Comparator.comparingLong(Parent::getId)).collect(Collectors.toList());
        } else if(orderParam.equals(StringUtils.ID) && orderByParam.equals(StringUtils.DESC)) {
            data = data.stream().sorted(Comparator.comparingLong(Parent::getId).reversed()).collect(Collectors.toList());
        }

        data.forEach(tr -> {
            List<Child> childTransactions = childDao.getList(tr.getId());
            tr.setTotalPaidAmount(childTransactions.stream().map(ch -> ch.getPaidAmount()).reduce(0L, Long::sum));
        });

        int fromIndex = (pagination.getPage() - 1) * pagination.getSize();
        if(data == null || data.size() <= fromIndex){
            return Collections.emptyList();
        }

        return data.subList(fromIndex, Math.min(fromIndex + pagination.getSize(), data.size()));
    }

    @Override
    public Parent findOne(Long id) {
        var result = readParentData();
        preconditionService.checkNotNull(result, MessageCodeConstants.PARENT_DATA_NOT_FOUND);
        return result.getData().stream().filter(tr -> tr.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Long countTotal() {
        var result = readParentData();
        preconditionService.checkNotNull(result, MessageCodeConstants.PARENT_DATA_NOT_FOUND);
        return result.getData().stream().count();
    }
}
