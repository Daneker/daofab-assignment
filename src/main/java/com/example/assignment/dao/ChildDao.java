package com.example.assignment.dao;

import com.example.assignment.model.Child;

import java.util.List;

public interface ChildDao {
    List<Child> getList(Long parentId);
}
