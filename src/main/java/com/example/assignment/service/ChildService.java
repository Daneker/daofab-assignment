package com.example.assignment.service;

import com.example.assignment.model.Child;

import java.util.List;

public interface ChildService {
    List<Child> getList(Long parentId);
}
