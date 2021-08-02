package com.example.assignment.model.dao;

import lombok.Getter;

// Needed for parsing child data from json
@Getter
public class ChildDto {
    private Long id;
    private Long parentId;
    private Long paidAmount;
}
