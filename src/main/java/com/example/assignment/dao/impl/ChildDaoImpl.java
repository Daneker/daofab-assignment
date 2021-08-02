package com.example.assignment.dao.impl;

import com.example.assignment.dao.ChildDao;
import com.example.assignment.model.Child;
import com.example.assignment.model.dao.ChildData;
import com.example.assignment.service.PreconditionService;
import com.example.assignment.util.constant.MessageCodeConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ChildDaoImpl implements ChildDao {

    private final PreconditionService preconditionService;

    @Value("${settings.child-path}")
    private String childPath;

    private ChildData readChildData() {
        var objectMapper = new ObjectMapper();
        ChildData result = null;
        try {
            result = objectMapper.readValue(new File(childPath), ChildData.class);
        } catch (Exception ex) {
            log.error("Exception: ", ex.getMessage());
        }
        return result;
    }

    @Override
    public List<Child> getList(Long parentId) {
        var result = readChildData();
        preconditionService.checkNotNull(result, MessageCodeConstants.CHILD_DATA_NOT_FOUND);

        return result.getData().stream().filter(child -> child.getParentId().equals(parentId)).map(child ->
            Child.builder()
                    .id(child.getId())
                    .paidAmount(child.getPaidAmount())
                    .build()
        ).collect(Collectors.toList());
    }
}
