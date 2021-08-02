package com.example.assignment.dao;

import com.example.assignment.model.Parent;
import com.example.assignment.model.pojo.Pagination;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ParentDao {
    List<Parent> getList(Pagination pagination, @Nullable String orderBy);
    Parent findOne(Long id);
    Long countTotal();
}
