package com.example.assignment.service;

import com.example.assignment.model.Parent;
import com.example.assignment.model.pojo.Page;
import com.example.assignment.model.pojo.Pagination;
import org.springframework.lang.Nullable;

public interface ParentService {
    Page<Parent> getList(Pagination pagination, @Nullable String orderBy);
}
